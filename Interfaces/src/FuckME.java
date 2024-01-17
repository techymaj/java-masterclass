public interface FuckME {
    boolean fucked();

    record MyRecord(String name) implements Skills {
        @Override
        public boolean hasAssets() {
            return false;
        }

        @Override
        public boolean knowJava() {
            return false;
        }

        @Override
        public boolean knowDart() {
            return false;
        }

        @Override
        public boolean knowCPlusPlus() {
            return false;
        }

        @Override
        public void goodThoughts() {

        }
    }
}
