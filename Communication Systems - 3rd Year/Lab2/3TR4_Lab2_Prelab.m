% clear;
% clc; 
% sample_rate= 300e3;f1 = 4000;f2 = 64000;range = 1:300;
% t = 0:(1/sample_rate):1; time_domain = cos(2*pi*f1*t).* cos(2*pi*f2*t);
% frequency_domain = fftshift(fft(time_domain)); 
% f=[-sample_rate/2:(length(frequency_domain)/sample_rate):(sample_rate/2)+1];
% figure(1);
% subplot (2,1,1); 
% plot(t(range), time_domain(range)); 
% title('DSBSC in time domain');
% subplot (2,1,2); 
% plot(f,abs(frequency_domain)); 
% title('DSBSC in frequency domain');
% 
% %------------------


clear;
clc;
rate = 300e3;
f1 = 4000;
f2 = 64000;
range = 1:300;
t = 0:(1/rate):1;
time_domain = cos(2*pi*f1*t).*cos(2*pi*f2*t);
freq_domain = fftshift(fft(time_domain));
f = [-rate/2:(length(freq_domain)/rate):(rate/2)+1];
figure(1);
subplot(2,1,1);
plot(t(range),time_domain(range));
title('xyz');
subplot(2,1,2);
plot(f,abs(freq_domain));
title('abc');