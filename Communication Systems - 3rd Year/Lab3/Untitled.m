% %Code for part 1
w = randn(40000,1); % % get long white random sequence w
decay = 0.8; % generate impulse response h(n)
t = 0:30;
h = exp(log(decay)*t); % this is equivalent to h(n) = 0.8^n 
y = conv(w,h);
maxlag = 85; %chosen to be 100 or under
Ry = xcorr(y,y,maxlag,'unbiased');
N = length(Ry);

Sy = (fft(Ry)); %fft of Ry
Sy = Sy(1:(N/2)+1); %turn into discrete
Sy = abs(Sy); %get absolute value of fft
freq_domain = 4000; %Hz

figure(1);
plot([-85:1:85],Ry);
title('Autocorrelation');
ylabel('Convolution');
xlabel('Tau');
grid on;
figure(2)
plot([0:(freq_domain/N):freq_domain/2], Sy);
title('Power Spectral Estimates');
ylabel('PSD');
xlabel('Normalized Frequency');
grid on;

%Code for part 2
load ydata; 
x = randn(40000,1);
maxlags = 85;
freq_domain = 4000;
t = -maxlags:1:maxlags;

%input section
Rx = xcorr(x,x,maxlags,'unbiased');
Rx = Rx/length(x);
N_x = length(Rx);
Sx = (fft(Rx));
Sx = Sx(1:(N_x/2)+1); %turn into discrete
Sx = abs(Sx); %get absolute value of fft
Freq_x = [0:(freq_domain/N_x):freq_domain/2];

%input plots
figure(3)
plot(t,Rx);
title('Input Autocorrelation');
xlabel('Time');
grid on;
figure(4)
plot(Freq_x,Sx);
title('Input PSD');
xlabel('Normalized Frequency');
grid on;

%output section
Ry = xcorr(y,y,maxlags,'unbiased');
N_y = length(Ry);
Sy = (fft(Ry));
Sy = Sy(1:(N_y/2)+1); %turn into discrete
Sy = abs(Sy); %get absolute value of fft
Freq_y = [0:(freq_domain/N_y):freq_domain/2];
 
%output plots
figure(5)
plot(t,Ry);
title('Output Autocorrelation');
xlabel('Time');
grid on;
figure(6)
plot(Freq_y,Sy);
title('Output PSD');
xlabel('Normalized Frequency');
grid on;

% %filter calculations
h = sqrt(Sy./Sx); %frequency domain
h = h(1:((N_x/2)+1));
h = abs(h); %absolute value of h in frequency domain
f = [0:(freq_domain/N_x):freq_domain/2]; %domain length in frequency


%filter plots
figure(7)
plot(f,h);
title('|H(f)| (Impulse response of filter)');
xlabel('Frequency');
grid on;


%Code for part 3
load part3;
fs = 1000; %Hz
maxlag = 85; %chosen to be 100 or under
Rx = xcorr(x,x,maxlag,'unbiased'); %autocorrelation of x
t = -maxlag:1:maxlag;
N_x = length(Rx);
Sx = fft(Rx); %fft of autocorrelation of x
Sx = Sx(1:(N_x/2)+1); %turn into discrete
Sx = abs(Sx); %get absolute value of fft
Freq_x = [0:(freq_domain/N_x):freq_domain/2];

%part 3 figures
figure(8)
plot(t,Rx);
title('Autocorrelation of X');
figure(9)
plot(Freq_x,Sx);
title('FFT of Autocorrelation');
[freq,amp]=ginput %read data off of matlab graph by clicking on point

