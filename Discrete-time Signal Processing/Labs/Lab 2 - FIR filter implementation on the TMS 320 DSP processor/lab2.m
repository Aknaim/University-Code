%Design a FIR filter in matlab

close all; 
clear all; 
clc;
set(0, 'DefaultFigureWindowStyle', 'docked');

%%Part 3:
load spf2; %load oringal speech signal (female version)

soundsc(speech); %play the original speech
speech_fft = fft(speech); %take the fft of original speech
speech_fft = fftshift(speech_fft); %shift the fft to start at 0
speech_angle = unwrap(angle(speech_fft)); %unwarped phase response of the signal

fs = 8000; %sampling frequency of dsp board

B = 0.1; %bandwidth of first lowpass with a cutoff of 0.1
sinc1 = B*sinc(B*(-50:50));
figure(1);
plot (1:length(sinc1),sinc1)
lowpass1 = sinc1.*hamming(101)'; %lowpass filter implementation 0.1
figure(10);
freqz(lowpass1);

C = 0.2; %bandwidth of first lowpass with a cutoff of 0.2
sinc2 = C*sinc(C*(-50:50));
figure(2);
plot (1:length(sinc2),sinc2)
lowpass2 = sinc2.*hamming(101)'; %lowpass filter implementation for 0.2

% figure;  freqz(lowpass, 1, 4096, 24e3);  %shg;
% %fvtool(sinc_in,1)
% zplane(lowpass, 1);
% figure;  zplane(lowpass.', 1);  %shg;
%return;

delta = zeros(1,length(lowpass1)); %creating the delta function with the same legnth as the lowpass filter
delta((length(delta)+1)/2)=1; %adding a 1 at the center of the zero vector

bandstop = delta - (lowpass2-lowpass1); %implementing the bandstop filter
freqz(bandstop); %plotting magnitude response of the bandstop filter
dlmwrite('bandcoeff.txt', bandstop) %printing the coefficients of the bandstop filter into a text file

% --- FIR1 function implementation ---
% n = 48; %filter order
% Wn = [0.125 0.25]; %filter range for bandstop [Wc_start, Wc_stop] normalized with respect to 4khz
% ftype = 'stop'; %type of filter
% 
% b = fir1(n,Wn,ftype); %create filter b coefficients
% a = 1; %a coefficients are set to one

% dlmwrite('bandcoeff.txt', b) %write the coefficients in ASCII format into the file

% figure(1);
% freqz(b,a,512) %plot the magnitude and phase response of the filter
% title('Magnitude and Phase Spectra of Filter')

a=1;
b=lowpass1;
%sf2=filter(a,b,speech);
sf2 = conv(speech,lowpass1);
soundsc(sf2); %play the filtered speech

sf2_fft = fft(sf2); %take the fft of the difference equation filter response
sf2_fft = fftshift(sf2_fft); %shift the fft to start at 0
sf2_angle = unwrap(angle(sf2_fft)); %unwarped phase response of the signal

figure(4);
subplot(2,1,1);
plot (linspace(-4000,4000,length(speech_fft)),abs(speech_fft)) %plot the magnitude response of the original speech
xlabel('Frequency (Hz)')
ylabel('Amplitude (Unitless)')
title('Original speech specta')

subplot(2,1,2);
plot(linspace(-4000,4000,length(speech_fft)),(speech_angle)) %plot the phase response of original speech
xlabel('Frequence (Hz)')
ylabel('Phase (Degrees)')

figure(5);
subplot(2,1,1);
plot (linspace(-4000,4000,length(sf2_fft)),abs(sf2_fft)) %plot the impulse response from the bandstop filter
xlabel('Frequency (Hz)')
ylabel('Amplitude (Unitless)')
title('Filtered Response from Impulse response filter')

subplot(2,1,2);
plot(linspace(-4000,4000,length(sf2_fft)),(sf2_angle)) %plot the phase response from the bandstop filter
xlabel('Frequence (Hz)')
ylabel('Phase (Degrees)')


sf2b = conv(speech,bandstop);
soundsc(sf2b); %play the filtered speech

sf2b_fft = fft(sf2b); %take the fft of the difference equation filter response
sf2b_fft = fftshift(sf2b_fft); %shift the fft to start at 0
sf2b_angle = unwrap(angle(sf2b_fft)); %unwarped phase response of the signal
figure(6);
subplot(2,1,1);
plot (linspace(-4000,4000,length(sf2b_fft)),abs(sf2b_fft)) %plot the impulse response from the bandstop filter
xlabel('Frequency (Hz)')
ylabel('Amplitude (Unitless)')
title('Filtered Response from Bandstop filter')

subplot(2,1,2);
plot(linspace(-4000,4000,length(sf2b_fft)),(sf2b_angle)) %plot the phase response from the bandstop filter
xlabel('Frequence (Hz)')
ylabel('Phase (Degrees)')

%Part 4
sample_frequency = 8192; %sample frequency of the signal
    sample_period = 1/sample_frequency; %sample period of the signal
    
number_of_sample_in_speech = numel(speech); %number of sample points in the speech
    net_time = number_of_sample_in_speech / sample_frequency; %the speech duration in time is equal to the number of samples divded by the sampling frequency
    
time_indices = 0:sample_period:(net_time - sample_period); 

tone_1 = 1 * sin(2 * pi * 600 * time_indices).';
    soundsc(tone_1, sample_frequency); %play the tone
    
time_indexes = 1:1:number_of_sample_in_speech; %each index of the speech is taken at a whole value from 0 to 21982

tone_2 = sin(2 * pi * 600 / sample_frequency * time_indexes).'; %we can add a tone that is of the same length at the speech by creating a sin function at 650 frequency
    soundsc(tone_2, sample_frequency);
    
speech_normalized = speech ./ max(abs(speech)); %we must normalize the speech so that it has the same domain as the tone

noisy_tone = tone_1 + speech_normalized; %speech + tone(at 600Hz)
    soundsc(noisy_tone, sample_frequency);
    
sf2b_tone = conv(noisy_tone,bandstop); %filtered speech+tone(at 600Hz)
    soundsc(sf2b_tone);

    
a = speech;
% spectrogram(8,192Hz)
% spectrogram code:
%     
[ S, F, T, P ] = spectrogram( a, 256, 128, 256, 8192 );
figure; surf(T,F,10*log10(P),'edgecolor','none'); axis tight; 
view(0,90);
xlabel('Time (Seconds)'); ylabel('Hz');

a = sf2;
% spectrogram(8,192Hz)
% spectrogram code:
%     
[ S, F, T, P ] = spectrogram( a, 256, 128, 256, 8192 );
figure; surf(T,F,10*log10(P),'edgecolor','none'); axis tight; 
view(0,90);
xlabel('Time (Seconds)'); ylabel('Hz');

a = tone_1;

% spectrogram(8,192Hz)
% spectrogram code:
%     
[ S, F, T, P ] = spectrogram( a, 256, 128, 256, 8192 );
figure; surf(T,F,10*log10(P),'edgecolor','none'); axis tight; 
view(0,90);
xlabel('Time (Seconds)'); ylabel('Hz');

a = noisy_tone;
% spectrogram(8,192Hz)
% spectrogram code:
    
[ S, F, T, P ] = spectrogram( a, 256, 128, 256, 8192 );
figure; surf(T,F,10*log10(P),'edgecolor','none'); axis tight; 
view(0,90);
xlabel('Time (Seconds)'); ylabel('Hz');

a = sf2b_tone;
% spectrogram(8,192Hz)
% spectrogram code:
    
[ S, F, T, P ] = spectrogram( a, 256, 128, 256, 8192 );
figure; surf(T,F,10*log10(P),'edgecolor','none'); axis tight; 
view(0,90);
xlabel('Time (Seconds)'); ylabel('Hz');

ydb = mag2db(abs(speech));
figure(15)
plot (ydb)




