package com.sinancompany.app;

// These IMPORTS ARE FOR WORD FREQ 
import java.util.Scanner;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.Map;

// THESE IMPORTS ARE FOR KUMO :D
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Color;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.palette.ColorPalette;
import com.kennycason.kumo.nlp.FrequencyFileLoader;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
 
 /* Hello world!
 *
 */
 
 public class App 
{
    public static void main( String[] args ) throws IOException
    {
        
		HashMap<String,Integer> wordfrequency = new HashMap <String, Integer> ();
		Scanner sc = new Scanner(new File("input.txt"));
		PrintStream ps = new PrintStream("output.txt");
		
		// ----- HASMAP TAKES IN THE VALUES OF CONTENTS OF INPUT.TXT -----
		while(sc.hasNext())
		{
			String word = sc.next().toLowerCase().replaceAll("[,.!]", "");
			
			if(wordfrequency.containsKey(word))
			{
				wordfrequency.put(word,wordfrequency.get(word) + 1);
			}
			else
			{
				wordfrequency.put(word,1);
			}
		}
		
		// -----HASHMAP IS CONVERTED INTO A LINKEDLIST AND SORTED-----
		List<Map.Entry<String,Integer>> olist = new LinkedList<Map.Entry<String, Integer>>(wordfrequency.entrySet());
		Collections.sort(olist, new Comparator<Map.Entry<String, Integer> >() 
		{
			public int compare(Map.Entry<String, Integer> o1,Map.Entry<String, Integer> o2) 
			{
				return (o2.getValue()).compareTo(o1.getValue());
			}
		  });
		
		// -----LINKEDLIST/MAP IS CONVERTED INTO A LINKEDHASHMAP -----
		HashMap<String, Integer> omap = new LinkedHashMap<String, Integer>(); 
		for (Map.Entry<String, Integer> aa : olist) 
			{
				omap.put(aa.getKey(), aa.getValue());
			}
		
		// ----- PRINTS WORD FREQ IN TEXT DOCUMENT -----
		for(String word: omap.keySet())
			{
				int count = omap.get(word);
				ps.println(count + ": " + word);
			}
		
		sc.close();
		ps.close();
		
		
		//THIS IS ALL FOR KUMO
		final FrequencyFileLoader frequencyFileLoader = new FrequencyFileLoader();
		final List<WordFrequency> wordFrequencies = frequencyFileLoader.load(new File("output.txt"));
		
		final Dimension dimension = new Dimension(600, 600);
		final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
		wordCloud.setPadding(2);
		wordCloud.setBackground(new CircleBackground(300));
		wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
		wordCloud.setFontScalar(new SqrtFontScalar(10, 80));
		wordCloud.build(wordFrequencies);
		wordCloud.writeToFile("circle.png");
		
    }
}
