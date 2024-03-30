package com.example.guchiBoard.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.Voice;
import com.example.guchiBoard.config.AWSConfig;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

/**
 * Polly Service
 */
@Service
public class PollyServiceImpl implements PollyService {

	private final AmazonPollyClient polly;
	private final Voice voice;

	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKeyID;

	@Value("${cloud.aws.credentials.accessKey}")
	private String secretAccessKey;

	// private static final Sring SAMPLE = "こんにちは、Pollyで音声読み上げ機能を作ってみたよ。やったね。";

	/*
	 * AWSCredentials credentials = new BasicAWSCredentials("...",
	 * "...");
	 */

	@Autowired
	private AWSConfig config;

	AWSCredentials credentials = new BasicAWSCredentials(accessKeyID, secretAccessKey);

	@Autowired
	public PollyServiceImpl(Region region) {
		// create an Amazon Polly client in a specific region
		polly = new AmazonPollyClient(new AWSStaticCredentialsProvider(credentials), new ClientConfiguration());

		/*
		 * polly = AmazonPollyClient.builder().withRegion(region) .withCredentials(new
		 * AWSStaticCredentialsProvider(credentials)).build(); polly.setRegion(region);
		 */
		// Create describe voices request.
		DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();

		// Synchronously ask Amazon Polly to describe available TTS voices.
		DescribeVoicesResult describeVoicesResult = polly.describeVoices(describeVoicesRequest);
		// voice = describeVoicesResult.getVoices().get(0);
		voice = describeVoicesResult.getVoices().stream().filter(v -> v.getName().equals("Takumi")).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Voice not found"));
	}

	@Override
	public InputStream synthesize(String text, OutputFormat format) throws IOException {
		SynthesizeSpeechRequest synthReq = new SynthesizeSpeechRequest().withText(text).withVoiceId(voice.getId())
				.withOutputFormat(format)/* .setEngine("standard") */;
		SynthesizeSpeechResult synthRes = polly.synthesizeSpeech(synthReq);

		return synthRes.getAudioStream();
	}

	@Override
	public void Polly(String text) throws Exception {
		// create the test class
		// PollyServiceImpl helloWorld = new
		// PollyServiceImpl(Region.getRegion(Regions.AP_NORTHEAST_1));
		PollyServiceImpl helloWorld = new PollyServiceImpl(config.awsRegion());
		// get the audio stream
		// InputStream speechStream = helloWorld.synthesize(SAMPLE, OutputFormat.Mp3);
		InputStream speechStream = helloWorld.synthesize(text, OutputFormat.Mp3);

		// create an MP3 player
		AdvancedPlayer player = new AdvancedPlayer(speechStream,
				javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());

		player.setPlayBackListener(new PlaybackListener() {
			@Override
			public void playbackStarted(PlaybackEvent evt) {
				System.out.println("Playback started");
				System.out.println(text);
			}

			@Override
			public void playbackFinished(PlaybackEvent evt) {
				System.out.println("Playback finished");
			}
		});

		// play it!
		player.play();

	}

}
