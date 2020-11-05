#include <iostream>
#include "mlp.hpp"
#include <png++/png.hpp>

using namespace std;

int main(int argc, char **argv)
{
	png::image <png::rgb_pixel> png_image(argv[1]);

	int size = png_image.get_width()*png_image.get_height();

	Perceptron *p = new Perceptron(3, size, 256, size); //3 a rétegek száma, ezt követő 3 érték a rétegenkénti neuronok száma

	double* image = new double[size];

	for(int i=0; i<png_image.get_width(); ++i)
		for(int j=0; j<png_image.get_height(); ++j)
			image[i*png_image.get_width()+j] = png_image[i][j].red;


	double* newPicture = (*p) (image); // eddig: double value = (*p)(image);

    
    for (int i = 0; i<png_image.get_width(); ++i)
		for (int j = 0; j<png_image.get_height(); ++j)
			png_image[i][j].red = newPicture[i*png_image.get_width()+j];
	
    png_image.write("kimeneti.png");

	delete p;
	delete [] image;
}
