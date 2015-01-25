package com.internetsam.search;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Search {
	public void searchStats(JTextArea txtrData, JTextField name, JSpinner start, JSpinner end, JComboBox type, JComboBox game, JComboBox time, JTextField link, JProgressBar progress) {
		
		int page = (int) start.getValue();
		String objective = null;
		String gamemode = null;
		String sortTime = null;
		try {
			
			switch (type.getSelectedIndex()) {
			case 0:
				objective = "kills";
				break;
			case 1:
				objective = "deaths";
				break;
			case 2:
				objective = "kd";
				break;
			case 3:
				objective = "kk";
				break;
			case 4:
				objective = "cores_leaked";
				break;
			case 5:
				objective = "wool_placed";
				break;
			case 6:
				objective = "destroyables_destroyed";
				break;
			case 7:
				objective = "playing_time";
				break;
			default:
				break;
			}
			
			switch (game.getSelectedIndex()) {
			case 0:
				gamemode = "all";
				break;
			case 1:
				gamemode = "projectares";
				break;
			case 2:
				gamemode = "blitz";
				break;
			case 3:
				gamemode = "ghostsquadron";
				break;
			default:
				break;
			}
			
			switch (time.getSelectedIndex()) {
			case 0:
				sortTime = "day";
				break;
			case 1:
				sortTime = "week";
				break;
			case 2:
				sortTime = "eternity";
				break;
			default:
				break;
			}
			
			while(page <= (int) end.getValue()) {
				Thread.sleep(250);
				String url = "https://oc.tc/stats?game="+gamemode+"&page="+page+"&sort="+objective+"&time="+sortTime;
				Document doc = Jsoup.connect(url).get();
				String pageData = doc.html();
				Document pageTextDoc = Jsoup.parse(pageData);
				String pageText = pageTextDoc.text();
				progress.setValue(100*page/(int)end.getValue());
				System.out.println(pageText);
				txtrData.setText("Not on page "+page+"\n"+100*page/(int)end.getValue());
				if(pageText.toLowerCase().contains(" " + name.getText().toLowerCase() + " ")) {
					
					progress.setValue(100);
					progress.setForeground(Color.green);
					txtrData.setText("You are on page "+page);
					link.setText(url);
					page = 0;
					break;
				}
				else
					page+=1;
			}if(page >= (int) end.getValue()) {
				
				txtrData.setText("We couldn't find you with the given parameters either the name you specified does not exist or it is beyond the page scope you entered!");
			}
			
		} catch (IOException e1) {

			e1.printStackTrace();
			txtrData.setText("Woops something went wrong");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
