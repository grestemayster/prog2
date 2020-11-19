char[] ASCII_PIXELS = { '$', '#', '*', ':', '.', ' ' };

public static char getAsciiChar(int pixel) {
    return getAsciiCharFromGrayScale(getGreyScale(pixel));
}

public static int getGreyScale(int argb) {
    int red = (argb >> 16) & 0xff;
    int green = (argb >> 8) & 0xff;
    int blue = (argb) & 0xff;
    return (red + green + blue) / 3;
}

public static char getAsciiCharFromGrayScale(int greyScale) {
    return ASCII_PIXELS[(int)Math.round(((float)greyScale / 51.0))];
}

return ASCII_PIXELS[(int)Math.round(((float)greyScale / 51.0))];

public void print(int w) throws IOException {
    int oldw = image.getWidth();
    int oldh = image.getHeight();
    int h = oldh * w / oldw;
    Image tmp = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

