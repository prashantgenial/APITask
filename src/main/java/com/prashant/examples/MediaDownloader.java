package com.prashant.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

public class MediaDownloader {

	private final ExecutorService executor = Executors.newFixedThreadPool(2);
	
	private static final long TIME_FRAME = 2000000000L; // 2 seconds
	
	private static class BookDownloader implements Callable {
		private String input ;
		private Integer records ;
		public BookDownloader(String input,Integer records) {
			this.input = input;
			this.records = records ;
		}
		public Book call() {			
			return getBook();
		}
		
		private Book getBook(){
			RestTemplate restTemplate = new RestTemplate();
			Book book = restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes?q="+this.input+"&maxResults="+this.records, Book.class); 
	        return book;
		}
	}
	
	private static class TrackDownloader implements Callable {
		private String input ;
		private Integer records ;
		public TrackDownloader(String input,Integer records) {
			this.input = input;
			this.records = records ;
		}
		public Track call() {			
			return getTracks();
		}
		
		private Track getTracks(){
			RestTemplate restTemplate = new RestTemplate();
			String url = "https://itunes.apple.com/search?term="+this.input+"&limit="+this.records;
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
	        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));		
			messageConverters.add(converter);  
			restTemplate.setMessageConverters(messageConverters);  
			Track track = restTemplate.getForObject(url, Track.class);
			return track;
		}
	}
	

	
	public List<Media> go(String input,Integer records) {
		List<Media> medias = new ArrayList<>();
		Future<Book> bookFuture = executor.submit(new BookDownloader(input,records));
		Future<Track> trackFuture = executor.submit(new TrackDownloader(input,records));
		
		try {
			Book book = bookFuture.get(TIME_FRAME, TimeUnit.NANOSECONDS);
			List<Item> items = book.getItems();
			for(Item item: items) {
				Media m1 = new Media(item.getVolumeInfo().getTitle(),item.getVolumeInfo().getAuthors().get(0),MyMediaType.BOOK);
				if(!StringUtils.isEmpty(item.getVolumeInfo().getTitle()) && !StringUtils.isEmpty(item.getVolumeInfo().getAuthors()) && !medias.equals(m1))
					medias.add(m1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			bookFuture.cancel(true);
			System.out.println("\n\nBook Task is cancelled --> " + Thread.currentThread());
		}
		
		try {
			Track track = trackFuture.get(TIME_FRAME, TimeUnit.NANOSECONDS);
			List<Result> results = track.getResults();
			for(Result result: results) {
				Media m2 = new Media(result.getTrackName(),result.getArtistName(),MyMediaType.SONG);
				if(!StringUtils.isEmpty(result.getTrackName()) && !StringUtils.isEmpty(result.getArtistName()) && !medias.equals(m2))
					medias.add(m2);	
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			trackFuture.cancel(true);
			System.out.println("\n\nTrack Task is cancelled --> " + Thread.currentThread());
		}

		executor.shutdown();
		
		Collections.sort(medias,new Comparator<Media>(){
      	  public int compare(Media p1, Media p2){
  		    return p1.getTitle().compareTo(p2.getTitle());
  		  }
  		}); 
		
	 return medias;

}
}