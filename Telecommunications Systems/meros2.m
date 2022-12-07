Es = 1;
EsN0Db = input("Valte to Es/N0(dB): ");
EsN0 = 10^(0.1*EsN0Db);
N0 = Es/EsN0;
SERtheoritiko = 2*qfunc(sqrt(1/N0)) - qfunc(sqrt(1/N0))^2;
s1 = sqrt(2*Es)/2 + 1j * sqrt(2*Es)/2;
s2 = sqrt(2*Es)/2 - 1j * sqrt(2*Es)/2;
s3 = -sqrt(2*Es)/2 - 1j * sqrt(2*Es)/2;
s4 = -sqrt(2*Es)/2 + 1j * sqrt(2*Es)/2;
error = 0;
for a = 1:1000000 
    s = randi(4);
    switch s
        case 1
            s = s1;
        case 2
            s = s2;
        case 3
            s = s3;
        otherwise
            s = s4;
    end
    n1 = randn() * sqrt(N0/2);
    n2 = randn() * sqrt(N0/2);
    n = n1 + 1j * n2;
    r = s + n;
    mlds1 = sqrt((real(r)-real(s1))^2 +(imag(r)-imag(s1))^2);
    mlds2 = sqrt((real(r)-real(s2))^2 +(imag(r)-imag(s2))^2);
    mlds3 = sqrt((real(r)-real(s3))^2 +(imag(r)-imag(s3))^2);
    mlds4 = sqrt((real(r)-real(s4))^2 +(imag(r)-imag(s4))^2);
    mld = min([mlds1 mlds2 mlds3 mlds4]);
    if mld==mlds1 
        if s ~= s1
            error = error+1;
        end
    elseif mld==mlds2
        if s ~= s2
            error = error+1;
        end
    elseif mld==mlds3 
        if s ~= s3
            error = error+1;
        end
    elseif mld==mlds4 
        if s ~= s4
            error = error+1;
        end
    end
end
SER = error / 1000000 ;
disp("experimental SER: " + SER);
disp("theoritical SER: " + SERtheoritiko);
