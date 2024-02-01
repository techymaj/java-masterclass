import Exceptions.NotInClassicModeException;

public enum Rank implements IsClassic {
    TWO(20) {
        @Override
        public Rank isClassic() {
            return Rank.TWO;
        }
    },
    THREE(3) {
        @Override
        public Rank isClassic() {
            if (Rules.isCLASSIC) {
                Rank.THREE.value = 30;
            } else {
                System.out.println("Not in classic mode");
            }
            return Rank.THREE;
        }
    },
    FOUR(4) {
        @Override
        public Rank isClassic() {
            return Rank.FOUR;
        }
    },
    FIVE(5) {
        @Override
        public Rank isClassic() {
            return Rank.FIVE;
        }
    },
    SIX(6) {
        @Override
        public Rank isClassic() {
            return Rank.SIX;
        }
    },
    SEVEN(7) {
        @Override
        public Rank isClassic() {
            return Rank.SEVEN;
        }
    },
    EIGHT(8) {
        @Override
        public Rank isClassic() {
            return Rank.EIGHT;
        }
    },
    NINE(9) {
        @Override
        public Rank isClassic() {
            return Rank.NINE;
        }
    },
    TEN(10) {
        @Override
        public Rank isClassic() {
            return Rank.TEN;
        }
    },
    JAY(11) {
        @Override
        public Rank isClassic() {
            return Rank.JAY;
        }
    },
    QUEEN(12) {
        @Override
        public Rank isClassic() {
            return Rank.QUEEN;
        }
    },
    KING(13) {
        @Override
        public Rank isClassic() {
            return Rank.KING;
        }
    },
    ACE(15) {
        @Override
        public Rank isClassic() {
            return Rank.ACE;
        }
    },
    KIZIKIZA(60) {
        @Override
        public Rank isClassic() {
            return Rank.KIZIKIZA;
        }
    },
    JOKER(0) {
        @Override
        public Rank isClassic() throws NotInClassicModeException {
            if (Rules.isCLASSIC) {
                Rank.JOKER.value = 50;
            } else {
                throw new NotInClassicModeException("Not in classic mode");
            }
            return Rank.JOKER;
        }
    };

    private int value;

    private Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getRank() {
        return this.ordinal();
    }
}

interface IsClassic {
    Rank isClassic() throws NotInClassicModeException;
}
