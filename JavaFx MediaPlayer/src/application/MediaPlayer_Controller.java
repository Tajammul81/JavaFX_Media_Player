package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MediaPlayer_Controller implements Initializable {
	@FXML private MediaView mv;
	@FXML private Slider volume;
	private Media me;
	private MediaPlayer mp;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String path= new File("src/media/yeratein.mp4").getAbsolutePath();
		me= new Media(new File(path).toURI().toString());
		mp= new MediaPlayer(me);
		mv.setMediaPlayer(mp);
		//mp.setAutoPlay(true); //its will load the song as the app as it runs successfully.
				// AS we have now play option (play Button below) so its not really needed
		DoubleProperty width= mv.fitWidthProperty();
		DoubleProperty height= mv.fitHeightProperty();
		// need to fit the video in screen so changing the values through doubleproperty.
		width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
		volume.setValue(mp.getVolume() * 100);
		/*
		 * the default value is 1 and its between 0 and 1 but on screen builder
		 *  its value is between 0 and 100.Therefore we will multiply it with hundred.
		 */
		volume.valueProperty().addListener(new InvalidationListener(){

			@Override
			public void invalidated(Observable observable) {
				mp.setVolume(volume.getValue()/100);
				// to match the value of mediaplayer object we will divide it by 100.
			}
			
		});
		
	}
	public void play(ActionEvent event){
		mp.play();
		mp.setRate(1);	// so this will make the video set to the normal rate.
	}

	public void pause(ActionEvent event){
		mp.pause();
	}
	public void fast(ActionEvent event){
		mp.setRate(2);
		// by default the value is 1 so if we want to double so we will have to make it 2.
		
	}
	public void slow(ActionEvent event){
		mp.setRate(0.5);
	}
	public void reload(ActionEvent event){
		mp.seek(mp.getStartTime());
		mp.play();
	}
	public void start(ActionEvent event){
		mp.seek(mp.getStartTime());
		mp.stop();
	}
	public void last(ActionEvent event){
		mp.seek(mp.getTotalDuration());
		mp.stop();
	}
	
	
			
		
		
		
			
			
			
			
					
					
			
			
	
		
}
