# Telecommunications Systems

## The exercises were done with [Octave](https://octave.org/).

---

### Below you will find the detailed description of the assignments along with graphs of the results etc.

---

## Part 1

### Simulation of an AM configuration and asynchronous demodulation using Octave. The information signal is m(t) = cos(2πt).

- **graph** of m(t) samples with sampling frequency fs = 10000 Hz and fs=10000 Hz; Ts=1/fs; t=[0:Ts:10]; m=cos(2*pi*t);

![image](https://user-images.githubusercontent.com/91612373/206241624-1b9f6954-42f4-4312-9cae-5929e4690611.png)

- **graph** of the AM-modulated signal x(t) with same values as above but with Ac = 2, fc = 500 Hz.

![image](https://user-images.githubusercontent.com/91612373/206727673-9827c11c-8939-496f-b302-2ea003d3767f.png)

- **graph** of Εnvelope of x(t), using: env=abs(hilbert(x)).

![image](https://user-images.githubusercontent.com/91612373/206727955-c2c75540-1394-43b8-9f8b-d007757574cf.png)

- **graph** of the original signal m(t) after the envelope. To get the original signal we have to lower the graph by Ac = 2.

![image](https://user-images.githubusercontent.com/91612373/206728311-37765e0b-0451-41da-afe7-d14356baccfc.png)

### Repeating the above calculations, with Ac = 0.7

- **graph** of the AM-modulated signal x(t) with same values as above but with Ac = 0.7, fc = 500 Hz.

![image](https://user-images.githubusercontent.com/91612373/206729512-d60d7488-683e-4d73-94ae-e7d01f9397ec.png)

- **graph** of Εnvelope of x(t), using: env=abs(hilbert(x)).

![image](https://user-images.githubusercontent.com/91612373/206729408-041a40a0-a2cf-4681-bcf4-1b19fdf30d89.png)

- **graph** of the original signal m(t) after the envelope. Due to overmodulation [ 1/Ac = 1/0.7 > 1 ] some information is lost. Hence the signal will be broadcasted with interruptions.

![image](https://user-images.githubusercontent.com/91612373/206729589-14f6a505-04c9-4347-b9a5-1ee7de83d579.png)

---

## Part 2

- **Es/No** = 5dB :

**Experimental SER** → 0.073835

**Theoretical SER** → 0.073938

**Deviation** : 0.000103

- **Es/No** = 8dB :

**Experimental SER** → 0.011902

**Theoretical SER** → 0.011973

**Deviation** : 0.000071

- **Es/No** = 10dB :

**Experimental SER** → 0.001595

**Theoretical SER** → 0.0015648

**Deviation** : 0.0000302
