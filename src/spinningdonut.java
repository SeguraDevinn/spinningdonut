public class spinningdonut {
    public static void main(String[] args) throws InterruptedException {

        int width = 40;
        int height = 22;
        double A = 0; //rotation angles
        double B = 0; //rotation angles
        double K1 = 15; //scale (do not change)
        double K2 = 5; //scale (do not change
        double R1 = 1; //inner radius (to create a sphere delete the inner hole R1 = 0)
        double R2 = 2; //outer radius
        double theta_spacing = 0.07; //do not change
        double phi_spacing = 0.02; //do not change

        char[] brightness = ".,-~:;=!*#$@".toCharArray();

        while (true) {
            double[] z = new double[width * height]; //brightness of pixel
            char[] b = new char[width * height]; //depth of each pixel

            for (int i = 0; i < z.length; i++) {
                z[i] = 0;
                b[i] = ' ';
            }
            for (double theta = 0; theta < 2 * Math.PI; theta += theta_spacing) {
                for (double phi = 0; phi < 2 * Math.PI; phi += phi_spacing) {
                    double cosTheta = Math.cos(theta);
                    double sinTheta = Math.sin(theta);
                    double cosPhi = Math.cos(phi);
                    double sinPhi = Math.sin(phi);
                    double cosA = Math.cos(A);
                    double sinA = Math.sin(A);
                    double cosB = Math.cos(B);
                    double sinB = Math.sin(B);
                    double circleX = R2 + R1 * cosTheta;
                    double circleY = R1 * sinTheta;

                    double x = circleX * (cosB * cosPhi + sinA * sinB * sinPhi)
                            - circleY * cosA * sinB;
                    double y = circleX * (sinB * cosPhi - sinA * cosB * sinPhi)
                            + circleY * cosA * cosB;
                    double zPos = K2 + cosA * circleX * sinPhi + circleY * sinA;
                    double ooz = 1 / zPos;

                    int xp = (int) (width / 2 + K1 * ooz * x);
                    int yp = (int) (height / 2 - K1 * ooz * y);
                    int idx = xp + yp * width;

                    double luminance = cosPhi * cosTheta * sinB - cosA
                            * cosTheta * sinPhi - sinA * sinTheta + cosB
                            * (cosA * sinTheta - cosTheta * sinPhi * sinA);
                    if (idx >= 0 && idx < width * height && ooz > z[idx]) {
                        z[idx] = ooz;
                        int luminanceIndex = (int) (8 * luminance);
                        if (luminanceIndex > 0) {
                            b[idx] = brightness[luminanceIndex];
                        }
                    }
                }
            }


            for (int k = 0; k < width * height; k++) {
                System.out.print(k % width != 0 ? b[k] : "\n");
            }
                A += 0.04;
                B += 0.02;
                Thread.sleep(50);

        }
    }
}
