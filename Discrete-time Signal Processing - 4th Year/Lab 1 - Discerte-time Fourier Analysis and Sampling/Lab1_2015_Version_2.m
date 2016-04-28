close all; clear all; clc;
set(0, 'DefaultFigureWindowStyle', 'docked');


%% Part 1

load spf2; %load oringal speech signal (female version)
    soundsc(speech,16e3); %play the original speech

speech_fft = fft(speech); %take the fft of original speech
speech_fft = fftshift(speech_fft); %shift the fft to start at 0

figure(1);
    plot (linspace(-4000,4000,length(speech_fft)),speech_fft); %plot the original speech spectra
    xlabel('frequency (w)');  title('Original speech specta')
%
% Check:
%   a.) Frequency-axis values (see my e-mail).
%   b.) Scaling of y-axis (see example in Matlab's help for the FFT function).
%   c.) You want the frequency-magnitude response, so you need to take the
%       absolute value for your y-argument in the PLOT function.  Warning
%       messages can be helpful - "Warning: Imaginary parts of complex X
%       and/or Y arguments ignored".


% Play around with this code.  The 8192 argument is the sample-frequency of
% the "speech" vector.
[X, freq, resolution] = centeredFFT(speech, 8192);
%
 figure('Name', 'Original Female Speech'); ...
    plot(freq, abs(X));
    xlabel('Frequency (Hz)');  ylabel('Amplitude (unitless)');
    grid on;

    
load impulse.mat; %load the impulse response
load coeffs.mat; %load the difference equation coefficients

sf1 = conv(hw,speech); %filter using the impulse response
sf2 = filter(b,a,speech); %filter using the difference equation coeffs

soundsc(sf1); %play the impulse filtered version
soundsc(sf2); %play the difference equation coeffs filtered version

sf1_fft = fft(sf1); %take the fft of the impulse filter response
sf1_fft = fftshift(sf1_fft); %shift the fft to start at 0
sf2_fft = fft(sf2); %take the fft of the difference equation filter response
sf2_fft = fftshift(sf2_fft); %shift the fft to start at 0

hw_fft = fft(hw); %take the fft of the impulse filter response
hw_fft = fftshift(hw_fft); %shift the fft to start at 0

figure(2);
plot (linspace(-4000,4000,length(sf1_fft)),sf1_fft); %plot the impulse response from the first filter
xlabel('frequency (w)')
title('Filtered response due to impulse filter')

figure(3);
plot (linspace(-4000,4000,length(sf2_fft)),sf2_fft); %plot the impulse reponse from the second filter
xlabel('frequency (w)')
title('Filtered response due to difference equation coefficients filter')

figure(4);
plot (linspace(-4000,4000,length(hw_fft)),abs(hw_fft)); %plot the spectra of the first filer
xlabel('frequency (w)')
title('Spectra of the impulse response filter')

figure(5);
freqz(b,a);
xlabel('frequency (w)')
title('Spectra of difference equation coefficients filter')


%% Part 2

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


%% Part 3

ln = length(sf1); %downsample the filtered speech
sfd = zeros(1,ln); 
sfd(1:2:ln)= sf1(1:2:ln); 
soundsc(sfd); %play the downsampled version of the speech

sfd_fft = fft(sfd); %take the fft of the impulse filter response
sfd_fft = fftshift(sfd_fft); %shift the fft to start at 0

figure(7);
plot (linspace(-4000,4000,length(sf1_fft)),sfd_fft); %plot the impulse response from the first filter
xlabel('frequency (w)')
title('Downsampled filtered speech spectra "sfd"')


%% Part 4

%We can simply use a LPF to filter out the downsampled components from the
%sfd signal such that it would match the original filtered speech from
%part 1

sfd = conv(hw,sfd); %filter the downsampled filter reponse using the impulse response
sfd_fft = fft(sfd); %take the fft of the impulse filter response
sfd_fft = fftshift(sfd_fft); %shift the fft to start at 0
soundsc(sfd);

figure(8);
plot (linspace(-4000,4000,length(sfd)),sfd_fft); %plot the filtered downsampled speech sfd
xlabel('frequency (w)')
title('Impulse response filtered downsampled speech')


%% Part 5:

load impulsegd.mat; %load the impulsegd file

sf3 = conv(h,speech); %filtered reponse due to the impulsegd filter
soundsc(sf3); %play the filtered speech due to the impulsegd filter

sf3_fft = fft(sf3); %take the fft of the impulsegd filter response
sf3_fft = fftshift(sf3_fft); %shift the fft to start at 0

figure(9);
plot (linspace(-4000,4000,length(sf3_fft)),sf3_fft); %plot the filtered downsampled speech sfd
xlabel('frequency (w)')
title('Impulsegd response filtered speech')

A1 = angle(speech_fft); %phase response of the original speech
figure(10);
plot (linspace(-4000,4000,length(speech_fft)),A1)

Q1 = unwrap (A1); %unwrap the phase response of the original speech
figure(11);
plot (linspace(-4000,4000,length(speech_fft)),Q1)

A2 = angle(sf3_fft); %phase response of the filtered speech that is wrapped
figure(12);
plot (linspace(-4000,4000,length(sf3_fft)),A2)

Q2 = unwrap (A2); %unwrap the phase response of the filtered speech loaded file
figure(13);
plot (linspace(-4000,4000,length(sf3_fft)),Q2)

figure(14);
freqz(h); %magnitude and phase spectra of the impulsegd filter
title('Magnitude and Phase spectra of the impulsegd filter')


%% Part 6

% fdatool;
% I designed the lowpass filter using the specifications from the screen
% captures you provided and created a Matlab function that creates the
% filter.  This function can be called from you script-file, as shown
% below, to bring the filter into the scope of your Matlab script-file.

h_lp = lowpass_2_kHz;

% In order to create a bandpass filter using this lowpass filter as the
% prototype filter, the poles and zeros in the complex z-plane must be
% rotated to that the DC point in the lowpass frequency-magnitude response
% is centered at the required center frequency of the bandpass filter.
%
% As a starting point, it is helpful to understand how the poles and zeros
% of the lowpass filter look in the complex z-plane.  This can be done
% using the Matlab function "zplance".

Num = h_lp.Numerator;

figure(15);    
figure();  zplane(h_lp.numerator);
figure();  freqz(h_lp.numerator, 1, 4096, 48e3);
%         freqz(Num); %magnitude and phase spectra of the Low Pass filter
%         title('Magnitude and Phase spectra of the Low Pass filter')

%To convert the LPF to Bandpass we need to multiple the function by
%cos(2*Pi*f0*n)
length_Num = 1:length(Num); %load the length of the LPF as a vector
% Num_Bandpass = Num.*cos(2*pi*3000*length_Num); %multiply each component by cos to shift up and down
Num_Bandpass = Num.*cos(2*pi*3000/48e3*length_Num); %multiply each component by cos to shift up and down

figure(16);
freqz(Num_Bandpass); %magnitude and phase spectra of the BandPass filter
title('Magnitude and Phase spectra of the Bandpss filter')
figure;  zplane(Num_Bandpass);