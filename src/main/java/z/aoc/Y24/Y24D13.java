package z.aoc.Y24;

import z.aoc.util.Day;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Y24D13 extends Day {
    List<String> lines = getInputList();
    static long count_part1 = 0;
    static long count_part2 = 0;
    @Override
    public Object part1() {
        List<ButtonPrize> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fetchFilePath()))) {
            String line;
            ButtonPrize current = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Button A:")) {
                    String[] parts = line.split(":")[1].split(",");
                    current = new ButtonPrize();
                    current.buttonAX = Integer.parseInt(parts[0].trim().split("\\+")[1]);
                    current.buttonAY = Integer.parseInt(parts[1].trim().split("\\+")[1]);
                } else if (line.startsWith("Button B:")) {
                    String[] parts = line.split(":")[1].split(",");
                    current.buttonBX = Integer.parseInt(parts[0].trim().split("\\+")[1]);
                    current.buttonBY = Integer.parseInt(parts[1].trim().split("\\+")[1]);
                } else if (line.startsWith("Prize:")) {
                    String[] parts = line.split(":")[1].split(",");
                    current.prizeX = Integer.parseInt(parts[0].trim().split("=")[1]);
                    current.prizeY = Integer.parseInt(parts[1].trim().split("=")[1]);
                    data.add(current);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (ButtonPrize bp : data) {
            //System.out.println(bp.getButtonAX() + "," + bp.getButtonAY() + "," + bp.getButtonBX() + "," + bp.getButtonBY() + "," + bp.getPrizeX() + "," + bp.getPrizeY());
            count_part1 += calculateSimultaneousEquations(bp.getButtonAX(), bp.getButtonAY(), bp.getButtonBX(), bp.getButtonBY(), bp.getPrizeX(), bp.getPrizeY());
        }
        return count_part1;
    }


    private static long calculateSimultaneousEquations(int buttonAX, int buttonAY, int buttonBX, int buttonBY, long prizeX, long prizeY) {
        int determinant = buttonAX * buttonBY - buttonBX * buttonAY;

        // If determinant is 0, there's no valid solution
        if (determinant == 0) return 0;

        // Calculate m and n using the formulas for simultaneous equations
        long mNumerator = prizeX * buttonBY - prizeY * buttonBX;
        long nNumerator = prizeY * buttonAX - prizeX * buttonAY;

        // Check if both m and n are integers
        if (mNumerator % determinant != 0 || nNumerator % determinant != 0) return 0;

        long m = mNumerator / determinant;
        long n = nNumerator / determinant;

        // If either m or n is negative, it's an invalid solution
        if (m < 0 || n < 0) return 0;

        // Calculate the token cost for the valid solution
        long tokenA = m * 3;  // A button costs 3 tokens per press
        long tokenB = n * 1;  // B button costs 1 token per press

        return tokenA + tokenB;  // Total token cost
    }


    @Override
    public Object part2() {
        List<ButtonPrize> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fetchFilePath()))) {
            String line;
            ButtonPrize current = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Button A:")) {
                    String[] parts = line.split(":")[1].split(",");
                    current = new ButtonPrize();
                    current.buttonAX = Integer.parseInt(parts[0].trim().split("\\+")[1]);
                    current.buttonAY = Integer.parseInt(parts[1].trim().split("\\+")[1]);
                } else if (line.startsWith("Button B:")) {
                    String[] parts = line.split(":")[1].split(",");
                    current.buttonBX = Integer.parseInt(parts[0].trim().split("\\+")[1]);
                    current.buttonBY = Integer.parseInt(parts[1].trim().split("\\+")[1]);
                } else if (line.startsWith("Prize:")) {
                    String[] parts = line.split(":")[1].split(",");
                    current.prizeX = Integer.parseInt(parts[0].trim().split("=")[1]);
                    current.prizeY = Integer.parseInt(parts[1].trim().split("=")[1]);
                    data.add(current);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (ButtonPrize bp : data) {
            prependZeroes(bp);
            //System.out.println( "Prize: " + bp.getButtonAX() + "," + bp.getButtonAY() + "," + bp.getButtonBX() + "," + bp.getButtonBY() + "," + bp.getPrizeX() + "," + bp.getPrizeY());
            count_part2 += calculateSimultaneousEquations(bp.getButtonAX(), bp.getButtonAY(), bp.getButtonBX(), bp.getButtonBY(), bp.getPrizeX(), bp.getPrizeY());
        }
        return count_part2;
    }

    // create method to prepend 10000000000000 to PrizeX and PrizeY, for example: 8400 will be 10000000008400
    private void prependZeroes(ButtonPrize bp) {
        bp.setPrizeX(bp.getPrizeX() + 10000000000000L);
        bp.setPrizeY(bp.getPrizeY() + 10000000000000L);
    }

    public static class ButtonPrize {
        public int buttonAX;
        public int buttonAY;
        public int buttonBX;
        public int buttonBY;
        public long prizeX;
        public long prizeY;

        public void setPrizeX(long prizeX) {
            this.prizeX = prizeX;
        }

        public void setPrizeY(long prizeY) {
            this.prizeY = prizeY;
        }

        public int getButtonAX() {
            return buttonAX;
        }

        public int getButtonAY() {
            return buttonAY;
        }

        public int getButtonBX() {
            return buttonBX;
        }

        public int getButtonBY() {
            return buttonBY;
        }

        public long getPrizeX() {
            return prizeX;
        }

        public long getPrizeY() {
            return prizeY;
        }
    }


}
