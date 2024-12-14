package z.aoc.Y24;

import z.aoc.util.Day;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Y24D09 extends Day {
    List<String> lines = getInputList();


    @Override
    public Object part1() {

        List<Integer> disk = new ArrayList<>();
        boolean space = false;
        int id = 0;
        for (String character : lines.getFirst().split("")) {
            int num = Integer.parseInt(character);
            if (space) {
                for (int i = 0; i < num; i++) disk.add(-1);
            } else {
                for (int i = 0; i < num; i++) disk.add(id);
                id++;
            }
            space = !space;
        }

        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == -1) {
                int val = -1;
                while (val == -1) {
                    val = disk.removeLast();
                }
                if (disk.size() <= i) {
                    disk.add(val);
                    break;
                }
                disk.remove(i);
                disk.add(i, val);
            }
        }

        BigInteger count = BigInteger.ZERO;
        for (int i = 0; i < disk.size(); i++) {
            count = count.add(BigInteger.valueOf(i).multiply(BigInteger.valueOf(disk.get(i))));
        }
        return count;
    }

    @Override
    public Object part2() {
        List<Block> disk = new ArrayList<>();
        boolean space = false;
        int id = 0;
        for (String character : lines.getFirst().split("")) {
            int num = Integer.parseInt(character);
            if (space) {
                disk.add(new Block(num, -1));
            } else {
                disk.add(new Block(num, id));
                id++;
            }
            space = !space;
        }

        boolean foundSpace;
        int diskPlace = disk.size() - 1;
        while (diskPlace > 0) {
            Block work = disk.get(diskPlace);
            if (work.getId() == -1) {
                diskPlace--;
            } else {
                foundSpace = false;
                for (int i = 0; i < diskPlace; i++) {
                    Block possibleSpace = disk.get(i);
                    if (possibleSpace.getId() == -1) {
                        List<Block> blocks = possibleSpace.fit(work);
                        if (blocks != null) {
                            disk.remove(diskPlace);
                            disk.add(diskPlace, new Block(work.getSize(), -1));
                            disk.remove(i);
                            for (int j = blocks.size() - 1; j > -1; j--) {
                                disk.add(i, blocks.get(j));
                            }
                            foundSpace = true;

                            diskPlace = disk.size() - 1;
                            break;
                        }
                    }
                }
                if (!foundSpace) {
                    diskPlace--;
                }
            }
        }


        return getBigInteger(disk);
    }

    private static BigInteger getBigInteger(List<Block> disk) {
        BigInteger count = BigInteger.ZERO;
        int placement = 0;
        for (Block block : disk) {
            if (block.getId() != -1) {
                for (int j = 0; j < block.getSize(); j++) {
                    count = count.add(BigInteger.valueOf(placement).multiply(BigInteger.valueOf(block.getId())));
                    placement++;
                }
            } else {
                placement += block.getSize();
            }
        }
        return count;
    }

    public static class Block {
        private int size = 0;
        private int id = 0;

        public Block(int size, int id) {
            this.size = size;
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public List<Block> fit(Block work) {
            if (work.size > this.size) {
                return null;
            }
            List<Block> newList = new ArrayList<>();
            if (work.size == this.size) {
                newList.add(work);
                return newList;
            } else {
                newList.add(work);
                newList.add(new Block(this.size - work.size, -1));
                return newList;
            }
        }

        public int getSize() {
            return size;
        }
    }




//    public static String redoInput(String input) {
//        char[] chars = input.toCharArray();
//        StringBuilder sb = new StringBuilder();
//        int ID = 0;
//        int maxValue = 10;
//        for (int i = 0; i < chars.length; i++) {
//            //check if i is even
//            if (i % 2 == 0) {
//                int current = Character.getNumericValue(chars[i]);
//                // append 'i' current amount of times
//                //
//                for (int j = 0; j < current; j++) {
//                    int wrapped = ID % maxValue;
//                    sb.append(wrapped);
//                }
//                ID++;
//
//            } if (i % 2 == 1) {
//                int current = Character.getNumericValue(chars[i]);
//                // append a '.' current amount of times
//                for (int j = 0; j < current; j++) {
//                    sb.append('.');
//                }
//
//            }
//
//        }
//        return sb.toString();
//    }
//
//    public static String compactString(String modified) {
//        StringBuilder sb = new StringBuilder(modified);
//        int end = sb.length() - 1;
//
//        for (int i = 0; i < sb.length(); i++) {
//            if (sb.charAt(i) == '.') {
//                while (end > i && sb.charAt(end) == '.') {
//                    end--; // Skip '.' at the end
//                }
//                if (end > i) {
//                    sb.setCharAt(i, sb.charAt(end));
//                    sb.setCharAt(end, '.');
//                    end--;
//                }
//            }
//        }
//
//        // Remove all trailing '.'
//        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.') {
//            sb.deleteCharAt(sb.length() - 1);
//        }
//
//        return sb.toString();
//    }
//
//    public static int getChecksum(String input) {
//
//        char[] chars = input.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            int current = Character.getNumericValue(chars[i]);
//            checksum += i * current;
//        }
//        return checksum;
//    }
}
