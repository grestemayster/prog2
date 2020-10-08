#include<iostream>
#include<math.h>
#include<time.h>

long n16MODk(int n, int k) {
    long r = 1;
    
    int t = 1;
    
    while(t <= n) {
        t = t * 2;
    }

    if(t > n)
        t = t/2;

    
    
    while(true) {
        if(n >= t) {
            r = (16*r) % k;
            n = n - t;
        }

        t = t/2;

        if(t < 1)
            break;
            
        r = (r*r) % k;
    }

    return r;
}

double d16Sj(int d, int j) {
    double d16Sj = 0.0d;

    for(int i = 0; i <= d; ++i){
        d16Sj += (double)(n16MODk(d - i, 8 * i + j))/(double)(8 * i + j);
    }

    for(int i = d+1; i <= 2*d; ++i) {
        d16Sj += pow(16.0d, d - i) / (double)(8*i + j);
    }

    return d16Sj - floor(d16Sj);
}

int main() {
    double d16Pi = 0.0;

    double d16S1t = 0.0;
    double d16S4t = 0.0;
    double d16S5t = 0.0;
    double d16S6t = 0.0;

    int jegy = 0;
    
    clock_t delta = clock();

    for(int d=100000000; d<100000001; ++d) {
        d16Pi = 0.0;

        d16S1t = d16Sj(d, 1);
        d16S4t = d16Sj(d, 4);
        d16S5t = d16Sj(d, 5);
        d16S6t = d16Sj(d, 6);

        d16Pi = 4.0*d16S1t - 2.0*d16S4t - d16S5t - d16S6t;
        
        d16Pi = d16Pi - floor(d16Pi);
        
        jegy = (int)floor(16.0*d16Pi);
    }

    std::cout << jegy << std::endl;
    delta = clock () - delta;
    std::cout << static_cast<double>(delta)/CLOCKS_PER_SEC << std::endl;
}
