package ru.gb.qa.lesson04;

public class TriangleArea {

    public static void main(String[] args) throws Exception {
        calculateArea(10, 12 ,9);
    }

    public static double calculateArea(double sideA, double sideB, double sideC) throws Exception {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new Exception("Некорректный треугольник!");
        }

        if (sideA + sideB > sideC && sideB + sideC > sideA && sideA + sideC > sideB) {
            throw new Exception("Некорректный треугольник!");
        }


        double halfP = (sideA + sideB + sideC) / 2;
        double square = (halfP * (halfP - sideA) * (halfP - sideB) * (halfP - sideC));
        return Math.sqrt(square);
    }

}