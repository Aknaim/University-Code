close all; 
clear all; 
clc;
set(0, 'DefaultFigureWindowStyle', 'docked');

%%Part 1:
load spf2; %load oringal speech signal (female version)
soundsc(speech); %play the original speech

speech_fft = fft(speech); %take the fft of original speech
speech_fft = fftshift(speech_fft); %shift the fft to start at 0
speech_angle = unwrap(angle(speech_fft)); %unwarped phase response of the signal

figure(1);
subplot(2,1,1);
plot (linspace(-4000,4000,length(speech_fft)),speech_fft) %plot the original speech spectra magnitude
xlabel('Frequency (Hz)')
ylabel('Amplitude (Unitless)')
title('Original speech specta')

subplot(2,1,2);
plot(linspace(-4000,4000,length(speech_fft)),(speech_angle)) %plot the original speech spectra phase
xlabel('Frequence (Hz)')
ylabel('Phase (Degrees)')

load impulse.mat; %load the impulse response
load coeffs.mat; %load the difference equation coefficients

sf1 = conv(hw,speech); %filter using the impulse response
sf2 = filter(b,a,speech); %filter using the difference equation coeffs

soundsc(sf1); %play the impulse filtered version
soundsc(sf2); %play the difference equation coeffs filtered version

sf1_fft = fft(sf1); %take the fft of the impulse filter response
sf1_fft = fftshift(sf1_fft); %shift the fft to start at 0
sf1_angle = unwrap(angle(sf1_fft)); %unwarped phase response of the signal

sf2_fft = fft(sf2); %take the fft of the difference equation filter response
sf2_fft = fftshift(sf2_fft); %shift the fft to start at 0
sf2_angle = unwrap(angle(sf2_fft)); %unwarped phase response of the signal

hw_fft = fft(hw); %take the fft of the impulse filter response
hw_fft = fftshift(hw_fft); %shift the fft to start at 0
hw_angle = unwrap(angle(hw_fft)); %unwarped phase response of the signal

figure(2);
subplot(2,1,1);
plot (linspace(-4000,4000,length(sf1_fft)),sf1_fft) %plot the impulse response from the first filter
xlabel('Frequency (Hz)')
ylabel('Amplitude (Unitless)')
title('Filtered Response from Impulse response filter')

subplot(2,1,2);
plot(linspace(-4000,4000,length(sf1_fft)),(sf1_angle)) %plot the phase response from the first filter
xlabel('Frequence (Hz)')
ylabel('Phase (Degrees)')

figure(3);
subplot(2,1,1);
plot (linspace(-4000,4000,length(sf2_fft)),sf2_fft) %plot the impulse response from the second filter
xlabel('Frequency (Hz)')
ylabel('Amplitude (Unitless)')
title('Filtered Response from Difference equation coefficients filter')

subplot(2,1,2);
plot(linspace(-4000,4000,length(sf2_fft)),(sf2_angle)) %plot the phase response from the second filter
xlabel('Frequence (Hz)')
ylabel('Phase (Degrees)')

figure(4);
subplot (2,1,1);
plot(linspace(-4000,4000,length(hw_fft)),abs(hw_fft)) %plot the magnitude spectra of the first filer
xlabel('Frequency (Hz)')
ylabel('Magnitude')
title('Spectra of the Impulse Response Filter')

subplot (2,1,2);
plot (linspace(-4000,4000,length(hw_fft)),(hw_angle)) %plot the phase spectra of the first filer
xlabel('Frequence (Hz)')
ylabel('Phase (Degrees)')

figure(5);
freqz(b,a); %plot the magnitude and phase spectra of the second filter
%xlabel('Frequency (Hz)')
title('Spectra of difference equation coefficients filter')

%%Part 2:
ln = length(speech); %downsample the speech
dsspeech = zeros(1,ln); 
dsspeech(1:2:ln)= speech(1:2:ln); 
soundsc(dsspeech); %play the downsampled version of the speech

dsspeech_fft = fft(dsspeech); %take the fft of the impulse filter response
dsspeech_fft = fftshift(dsspeech_fft); %shift the fft to start at 0
dsspeech_angle = unwrap(angle(dsspeech_fft)); %unwarped phase response of the downsampled speech

figure(6);
subplot(2,1,1);
plot (linspace(-4000,4000,length(dsspeech_fft)),dsspeech_fft); %plot the magnitude response of the downsampled speech
xlabel('Frequency (Hz)')
ylabel('Amplitude (Unitless)')
title('Downsampled Original Speech Spectra')

subplot(2,1,2);
plot (linspace(-4000,4000,length(dsspeech_fft)),dsspeech_angle); %plot the phase response of the downsampled speech
xlabel('Frequence (Hz)')
ylabel('Phase (Degrees)')

%%Part 3:

ln = length(sf1); %downsample the filtered speech
sfd = zeros(1,ln); 
sfd(1:2:ln)= sf1(1:2:ln); 
soundsc(sfd); %play the downsampled version of the speech

sfd_fft = fft(sfd); %take the fft of the impulse filter response
sfd_fft = fftshift(sfd_fft); %shift the fft to start at 0
sfd_angle = unwrap(angle(sfd_fft)); %unwarped phase response of the filtered downsampled speech

figure(7);
subplot(2,1,1);
plot (linspace(-4000,4000,length(sfd_fft)),sfd_fft); %plot the impulse response of the filtered downsampled speech
xlabel('frequency (Hz)')
ylabel('Amplitude (Unitless)')
title('Downsampled Impulse Response Filtered Speech Spectra "sfd"')

subplot(2,1,2);
plot (linspace(-4000,4000,length(sfd_fft)),sfd_angle); %plot the phase response of the filtered downsampled speech
xlabel('Frequence (Hz)')
ylabel('Phase (Degrees)')

%%Part 4:

%We can simply use a LPF to filter out the downsampled components from the
%sfd signal such that it would match the original filtered speech from
%part 1

sfd_lpf = conv(hw,sfd); %filter the downsampled filter reponse using the impulse response
sfd_fft_lpf = fft(sfd_lpf); %take the fft of the impulse filter response
sfd_fft_lpf = fftshift(sfd_fft_lpf); %shift the fft to start at 0
sfd_angle_lpf = unwrap(angle(sfd_fft_lpf)); %unwarped phase response of the LPF downsampled speech

soundsc(sfd_lpf); %play the speech

figure(8);
subplot(2,1,1);
plot (linspace(-4000,4000,length(sfd_fft_lpf)),sfd_fft_lpf); %plot the impulse response of the LPF downsampled speech
xlabel('frequency (Hz)')
ylabel('Amplitude (Unitless)')
title('Low Pass Processed Downsampled Speech Spectra "sfd"')

subplot(2,1,2);
plot (linspace(-4000,4000,length(sfd_fft_lpf)),sfd_angle_lpf); %plot the phase response of the LPF downsampled speech
xlabel('Frequence (Hz)')
ylabel('Phase (Degrees)')

%%Part 5:

load impulsegd.mat; %load the impulsegd file

sf3 = conv(h,speech); %filtered reponse due to the impulsegd filter
soundsc(sf3); %play the filtered speech due to the impulsegd filter

sf3_fft = fft(sf3); %take the fft of the impulsegd filter response
sf3_fft = fftshift(sf3_fft); %shift the fft to start at 0
sf3_angle = unwrap(angle(sf3_fft)); %unwarped phase response of the impulsegd filtered speech

figure(9);
plot (linspace(-4000,4000,length(sf3_fft)),sf3_fft); %plot the filtered downsampled speech sfd
xlabel('Frequency (Hz)')
ylabel('Amplitude (Unitless)')
title('Impulsegd response filtered speech')

A1 = angle(speech_fft); %phase response of the original speech
figure(10);
subplot(2,1,1);
plot (linspace(-4000,4000,length(speech_fft)),A1)
xlabel('Frequence (Hz)')
ylabel('Phase (Degrees)')
title('Wrapped Phase Response of the Original Speech')

Q1 = unwrap (A1); %unwrap the phase response of the original speech
subplot(2,1,2);
plot (linspace(-4000,4000,length(speech_fft)),Q1)
xlabel('Frequence (Hz)')
ylabel('Phase (Degrees)')
title('Unwrapped Phase Response of the Original Speech')

A2 = angle(sf3_fft); %phase response of the filtered speech that is wrapped
figure(11);
subplot(2,1,1);
plot (linspace(-4000,4000,length(sf3_fft)),A2)
xlabel('Frequence (Hz)')
ylabel('Phase (Degrees)')
title('Wrapped Phase Response of the Impulsegd Filtered Speech')

Q2 = unwrap (A2); %unwrap the phase response of the filtered speech loaded file
subplot(2,1,2);
plot (linspace(-4000,4000,length(sf3_fft)),Q2)
xlabel('Frequence (Hz)')
ylabel('Phase (Degrees)')
title('Unwrapped Phase Response of the Impulsegd Filtered Speech')

figure(12);
freqz(h); %magnitude and phase spectra of the impulsegd filter
title('Magnitude and Phase spectra of the impulsegd filter')

%%Part 6:
% 
% fdatool;
% 
% figure(15);
% freqz(Num); %magnitude and phase spectra of the Low Pass filter
% title('Magnitude and Phase spectra of the Low Pass filter')
% 
% %To convert the LPF to Bandpass we need to multiple the function by
% %cos(2*Pi*f0*n)
% 
% length_Num = 1:length(Num); %load the length of the LPF as a vector
% Num_Bandpass = Num.*cos(2*pi*3000/48e3*length_Num); %multiply each component by cos to shift up and down
% 
% figure(16);
% freqz(Num_Bandpass); %magnitude and phase spectra of the BandPass filter
% title('Magnitude and Phase spectra of the Bandpss filter')

%% Part 6

fdatool;

h_lp = lowpass_2_kHz; %set the variable h_lp equal to the low pass filter coefficients

Num = h_lp.Numerator;

figure(13);  
freqz(h_lp.numerator, 1, 4096, 48e3); %magnitude and phase spectra of the Low Pass filter
title('Magnitude and Phase spectra of the Low Pass filter')

%To convert the LPF to Bandpass we need to multiple the function by
%cos(2*Pi*F0/Fs*n) where F0 = arbitrary shift, and Fs = samepling frequency

length_Num = 1:length(Num); %load the length of the LPF as a vector
Num_Bandpass = Num.*cos(2*pi*3000/48e3*length_Num); %multiply each component by cos to shift up and down

figure(14);
freqz(Num_Bandpass); %magnitude and phase spectra of the BandPass filter
title('Magnitude and Phase spectra of the Bandpss filter')

