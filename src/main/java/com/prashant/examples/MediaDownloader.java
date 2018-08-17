package com.prashant.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.prashant.examples.client.books.Book;
import com.prashant.examples.client.books.Item;
import com.prashant.examples.client.itunes.Result;
import com.prashant.examples.client.itunes.Track;
import com.prashant.examples.enums.MyMediaType;
import com.prashant.examples.model.Media;

@Configuration
@PropertySource("classpath:application.properties")
public class MediaDownloader {

	private final ExecutorService executor = Executors.newFixedThreadPool(2);
		
	private static final long TIME_FRAME = 2000000000L; // 2 seconds
	private static final String TRACKURL = "https://itunes.apple.com/search";
	private static final String BOOKURL = "https://www.googleapis.com/books/v1/volumes";
	
	private static class Downloader implements Callable<List<Media>> {
		private String input ;
		private Integer records ;
		private MyMediaType mediaType;
		public Downloader(String input,Integer records,MyMediaType mediaType) {
			this.input = input;
			this.records = records ;
			this.mediaType = mediaType ;
		}
		public List<Media> call() {
			if(this.mediaType == MyMediaType.BOOK)
				return getBook();
			else
				return getTracks();
		}
		
		private List<Media> getBook(){
			List<Media> medias = new ArrayList<>(records);
			RestTemplate restTemplate = new RestTemplate();
			Book book = restTemplate.getForObject(BOOKURL+"?q="+this.input+"&maxResults="+this.records, Book.class); 
			List<Item> items = book.getItems();
			items.forEach(item -> {
				if(!StringUtils.isEmpty(item.getVolumeInfo().getTitle()) && !StringUtils.isEmpty(item.getVolumeInfo().getAuthors()))
					medias.add(new Media(item.getVolumeInfo().getTitle(),item.getVolumeInfo().getAuthors().get(0),MyMediaType.BOOK));
				}
			);			
	        return medias;
		}
		
		private List<Media> getTracks(){
			List<Media> medias = new ArrayList<>(records);
			RestTemplate restTemplate = new RestTemplate();
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
	        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));		
			messageConverters.add(converter);  
			restTemplate.setMessageConverters(messageConverters);  
			Track track = restTemplate.getForObject(TRACKURL + "?term="+this.input+"&limit="+this.records, Track.class);
			List<Result> results = track.getResults();
			results.forEach(result -> {
				if(!StringUtils.isEmpty(result.getTrackName()) && !StringUtils.isEmpty(result.getArtistName()))
					medias.add(new Media(result.getTrackName(),result.getArtistName(),MyMediaType.SONG));
				}
			);
			return medias;
		}
	}

	
	public List<Media> go(String input,Integer records) {
		List<Media> medias = new ArrayList<>(2*records);
		List<Future<List<Media>>> futures = new ArrayList<>();
		futures.add(executor.submit(new Downloader(input,records,MyMediaType.BOOK)));
		futures.add(executor.submit(new Downloader(input,records,MyMediaType.SONG)));
		
		for (Future<List<Media>> future : futures) {
			try {
				List<Media> m = future.get(TIME_FRAME, TimeUnit.NANOSECONDS);
				medias.addAll(m);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				future.cancel(true);
				System.out.println("\n\n Task is cancelled --> " + Thread.currentThread());
			}
		}
		
		executor.shutdown();
		
		return medias.parallelStream().sorted((m1,m2) -> m1.getTitle().compareTo(m2.getTitle())).collect(Collectors.toList());
	}
}