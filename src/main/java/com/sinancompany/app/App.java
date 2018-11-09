package com.sinancompany.app;

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
        System.out.println( "Hello World!" );
		final FrequencyFileLoader frequencyFileLoader = new FrequencyFileLoader();
		final List<WordFrequency> wordFrequencies = frequencyFileLoader.load(new File("input.txt"));
		
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
