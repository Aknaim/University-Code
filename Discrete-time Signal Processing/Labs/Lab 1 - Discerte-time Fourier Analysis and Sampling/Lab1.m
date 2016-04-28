close all; 
clear all; 
clc;

%Procedure
%h.mat contains an impulse response for a LPF
%coeffs.mat contains the difference equation coefficients
%to realize anther form of low-pass filter function

%Part 1:
load spf2; %load oringal speech
soundsc(speech); %play the original speech

load impulse.mat;
load coeffs.mat;

sf1 = filter(hw,1,speech); %filter using the impulse response
sf2 = filter(b,a,speech); %filter using the difference equation coeffs

soundsc(sf1); %play the impulse filtered version
soundsc(sf2); %play the difference equation coeffs filtered version

sf1_fft = fft(sf1); %take the fft of the impulse filter response
sf1_fft = fftshift(sf1_fft); %shift the fft to start at 0
sf2_fft = fft(sf2); %take the fft of the difference equation filter response
sf2_fft = fftshift(sf2_fft); %shift the fft to start at 0


hw_fft = fft(hw); %take the fft of the impulse filter response
hw_fft = fftshift(hw_fft); %shift the fft to start at 0


speech_fft = fft(speech); %take the fft of the impulse filter response
speech_fft = fftshift(speech_fft); %shift the fft to start at 0


figure(1);
plot (linspace(-4000,4000,length(sf1_fft)),sf1_fft); %plot the impulse response from the first filter
xlabel('frequency (w)')
title('Response due to impulse filter')

figure(2);
plot (linspace(-4000,4000,length(sf2_fft)),sf2_fft); %plot the impulse reponse from the second filter
xlabel('frequency (w)')
title('Response due to difference equation coefficients filter')


figure(3);
plot (linspace(-4000,4000,length(hw_fft)),abs(hw_fft)); %plot the impulse response from the first filter
xlabel('frequency (w)')
title('Spectra of impulse response')

figure(4);
freqz(b,a);
xlabel('frequency (w)')
title('Spectra of difference equation coefficients response')

figure(5);
plot (linspace(-4000,4000,length(speech_fft)),speech_fft); %plot the impulse response from the first filter
xlabel('frequency (w)')
title('Original speech specta')


%Part 2:

ln = length(speech); %downsample the speech
dsspeech = zeros(1,ln); 
dsspeech(1:2:ln)= speech(1:2:ln); 
soundsc(dsspeech); %play the downsampled version of the speech

dsspeech_fft = fft(dsspeech); %take the fft of the impulse filter response
dsspeech_fft = fftshift(dsspeech_fft); %shift the fft to start at 0

figure(6);
plot (linspace(-4000,4000,length(speech_fft)),dsspeech_fft); %plot the impulse response from the first filter
xlabel('frequency (w)')
title('Downsampled speech spectra')

%Part 3:

ln = length(sf1_fft); %downsample the filtered speech sf1
sf1_dsspeech = zeros(1,ln); 
sf1_dsspeech(1:2:ln)= sf1_fft(1:2:ln); 
soundsc(sf1_dsspeech); %play the downsampled version of the speech

sf1_dsspeech_fft = fft(sf1_dsspeech); %take the fft of the impulse filter response
sf1_dsspeech_fft = fftshift(sf1_dsspeech_fft); %shift the fft to start at 0

figure(7);
plot (linspace(-4000,4000,length(sf1_fft)),sf1_dsspeech_fft); %plot the impulse response from the first filter
xlabel('frequency (w)')
title('Downsampled filtered sf impulse speech spectra')

