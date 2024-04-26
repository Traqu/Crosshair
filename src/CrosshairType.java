import java.util.Arrays;

public class CrosshairType {
        private String type;
        private String[] pattern;
        private boolean[][] activeFields;

        public CrosshairType() {
        }

        public void initializeActiveFields() {
            activeFields = new boolean[pattern.length][pattern.length];
            for (int i = 0; i < pattern.length; i++) {
                char[] charArray = pattern[i].toCharArray();
                for (int j = 0; j < pattern.length; j++) {
                    if(charArray[j] == '-') {
                        activeFields[i][j] = false;
                    } else {
                        activeFields[i][j] = true;
                    }
                }
                System.out.println();
            }

        }

        public String getType() {
            return type;
        }

        public String[] getPattern() {
            return pattern;
        }
    }
