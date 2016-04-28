close all; 
clear all; 
clc;
set(0, 'DefaultFigureWindowStyle', 'docked');

%Part 1

%design a 3rd order butterworth filter
%fdatool;
Hdb = butterworth_3;
Hdc = chebyshev_3;
Hde = elliptic_3;

%IIR 3rd order Butterworth specs:
fs = 8000;%Hz

B = 1450/(fs/2); %bandwidth of the sinc function normalized with respect to 4000
sinc_win = B*sinc(B*(-50:50));% .* hamming(numel(B)).';

output_b = filter(Hdb, sinc_win); %output of sinc function through butterworth 3rd order
output_c = filter(Hdc, sinc_win); %output of sinc function through chebyshev 3rd order
output_e = filter(Hde, sinc_win); %output of sinc function through elliptic 3rd order

figure(1);
xlabel('Samples') % x-axis label
ylabel('Amplitude') % y-axis label
title('sinc output functions')
hold on
plot (sinc_win ); %- sinc_win);
plot (output_b , 'r'); %- output_b, 'r')
plot (output_c , 'g'); %- output_c, 'g')
plot (output_e , 'b'); %- output_e, 'b')
legend( 'Unfiltered SINC', 'Butterworth', 'Chebyshev' ,'Eliptical');
grid on;  shg;

% ----- old code ----:
% [num,den] = tf(Hdb); %get transfer function of 3rd order butterworth
% num = [1 num];
% den = den;
% --------------------

%Part 3
fc = 1500;%Hz

[B1, A1] = butter (3, fc/(fs/2)); % 3rd order & normalized cutoff
    figure(2);  zplane(B1,A1);  title('Complex Plane of IIR Filter');

figure(13)
freqz(B1,A1);
[gd1, f] = grpdelay(B1,A1,50,fs);
    figure(3);  plot(f,gd1);  title('Group Delay of IIR Filter');

   
%type 1 equalizer
%has the form: (Z-a)/(Z-(1/a))
%magnitude response depends only on a
radius = 2.0;
B2 = [ 1  -radius];
A2 = [ 1  -(1/radius)];
[gd2, w] = grpdelay(B2,A2,50);
figure(4)
zplane(B2,A2);
title('Complex Plane of Type 1 Equalizer');
fvtool (B2,A2);
figure(6)
plot (w, gd2)
title('Group Delay of Type 1 Equalizer');


%type 2 equalizer
%has the form: 
%((Z-a2*exp(1i*p))*(Z-a2*exp(-1i*p))
%divided by (Z-1/a2*exp(1i*p))*(Z-1/a2*exp(-1i*p))
a2 = 2.2;
p=pi/3;

B3 = [1 -a2*exp(1i*p)-a2*exp(-1i*p) a2*exp(1i*p)*a2*exp(-1i*p)];
A3 = [1 -1/a2*exp(1i*p)- 1/a2*exp(-1i*p) 1/a2*exp(1i*p)*1/a2*exp(-1i*p)];
[gd3, w] = grpdelay(B3,A3,50);
figure(7)
zplane(B3,A3);
title('Complex Plane of Type 1 Equalizer');
figure(8)
plot (w, gd3)
title('Group Delay of Type 2 Equalizer');

%total group delay
figure(9)
plot (w, gd1+gd2+gd3)
hold on
plot (w,gd1)
plot (w,gd2)
plot (w,gd3)
legend( 'Total', 'IIR', 'Type I', 'Type II' );
title('Total Group Delay: IIR+Type1+Type2');

%Part 6
%putting the sinc function through the 3 stages of the delay equalizer
%sinc_win = orignal sinc function defined in part 1

output_IIR = filter(B1,A1,sinc_win); %output after IIR filter
figure(10)
plot(output_IIR);
hold on

output_IIR_T1 = filter(B2,A2,output_IIR); %output after IIR+Type1 Equalizer
plot(output_IIR_T1);

output_IIR_T1_T2 = filter(B3,A3,output_IIR_T1); %output after IIR+Type1+Type2 Equalizer
plot(output_IIR_T1_T2);
xlabel('Samples') % x-axis label
ylabel('Amplitude') % y-axis label
legend( 'IIR', 'IIR+TypeI', 'IIR+Type1+TypeII' );

%Part 6
%Compare the impulse response of the unequalized filter alone and of the
%overall stucture. Does the latter look more like a sinc function.

[H1,T1] = impz(B1,A1); %impulse response of the unequalized butterworth 3rd order
figure(11)
plot(T1,H1)

hold on

%impulse response due to the overall system
%take the convolution of the each response in series
[H2,T2] = impz(B2,A2);
[H3,T3] = impz(B3,A3);

H_T = conv(H1,H2);
H_T = conv(H_T,H3);

plot(H_T);

title('Butterworth 3rd Order Impulse Response vs Total Impulse Response of Equalizer');
legend('IIR Butterworth 3rd order Impulse Response', 'Total Impulse Response of Equalizing System');
xlabel('Samples'); % x-axis label
ylabel('Amplitude'); % y-axis label

%figure(15)

%fvtool(T1.*T2.*T2, H3.*H2.*H1);

%H_O = (H1.*H2.*H3);
%T_O = T1; %impulse response due to the overall system
%plot(T_O,H_O)
% xlabel('Samples') % x-axis label
% ylabel('Amplitude') % y-axis label
% legend ('IIR Impulse Response', 'Total Impulse Response');

% figure(12)
% fvtool (B1,A1)
% hold on
% fvtool (B2,A2)
% fvtool (B3,A3)
% fvtool (B1.*B2.*B3,A1.*A2.*A3)
% xlabel('Samples') % x-axis label
% ylabel('Amplitude') % y-axis label
% legend ('IIR Impulse Response', 'Total Impulse Response');




