package de.tzimom.javarobot.graphics.rendering.animation;

public enum EasingCurve implements Curve {
    LINEAR {
        public double getValueAt(double x) {
            return x;
        }
    },
    EASE_IN_SINE {
        public double getValueAt(double x) {
            return 1 - Math.cos((x * Math.PI) / 2);
        }
    },
    EASE_OUT_SINE {
        public double getValueAt(double x) {
            return Math.sin((x * Math.PI) / 2);
        }
    },
    EASE_IN_OUT_SINE {
        public double getValueAt(double x) {
            return -(Math.cos(Math.PI * x) - 1) / 2;
        }
    },
    EASE_IN_QUAD {
        public double getValueAt(double x) {
            return x * x;
        }
    },
    EASE_OUT_QUAD {
        public double getValueAt(double x) {
            return 1 - (1 - x) * (1 - x);
        }
    },
    EASE_IN_OUT_QUAD {
        public double getValueAt(double x) {
            return x < 0.5 ? 2 * x * x : 1 - Math.pow(-2 * x + 2, 2) / 2;
        }
    },
    EASE_IN_CUBIC {
        public double getValueAt(double x) {
            return x * x * x;
        }
    },
    EASE_OUT_CUBIC {
        public double getValueAt(double x) {
            return 1 - Math.pow(1 - x, 3);
        }
    },
    EASE_IN_OUT_CUBIC {
        public double getValueAt(double x) {
            return x < 0.5 ? 4 * x * x * x : 1 - Math.pow(-2 * x + 2, 3) / 2;
        }
    },
    EASE_IN_QUART {
        public double getValueAt(double x) {
            return x * x * x * x;
        }
    },
    EASE_OUT_QUART {
        public double getValueAt(double x) {
            return 1 - Math.pow(1 - x, 4);
        }
    },
    EASE_IN_OUT_QUART {
        public double getValueAt(double x) {
            return x < 0.5 ? 8 * x * x * x * x : 1 - Math.pow(-2 * x + 2, 4) / 2;
        }
    },
    EASE_IN_QUINT {
        public double getValueAt(double x) {
            return x * x * x * x * x;
        }
    },
    EASE_OUT_QUINT {
        public double getValueAt(double x) {
            return 1 - Math.pow(1 - x, 5);
        }
    },
    EASE_IN_OUT_QUINT {
        public double getValueAt(double x) {
            return x < 0.5 ? 16 * x * x * x * x * x : 1 - Math.pow(-2 * x + 2, 5) / 2;
        }
    },
    EASE_IN_EXPO {
        public double getValueAt(double x) {
            return x == 0 ? 0 : Math.pow(2, 10 * x - 10);
        }
    },
    EASE_OUT_EXPO {
        public double getValueAt(double x) {
            return x == 1 ? 1 : 1 - Math.pow(2, -10 * x);
        }
    },
    EASE_IN_OUT_EXPO {
        public double getValueAt(double x) {
            return x == 0
                    ? 0
                    : x == 1
                    ? 1
                    : x < 0.5 ? Math.pow(2, 20 * x - 10) / 2
                    : (2 - Math.pow(2, -20 * x + 10)) / 2;
        }
    },
    EASE_IN_CIRCLIC {
        public double getValueAt(double x) {
            return 1 - Math.sqrt(1 - Math.pow(x, 2));
        }
    },
    EASE_OUT_CIRCLIC {
        public double getValueAt(double x) {
            return Math.sqrt(1 - Math.pow(x - 1, 2));
        }
    },
    EASE_IN_OUT_CIRCLIC {
        public double getValueAt(double x) {
            return x < 0.5
                    ? (1 - Math.sqrt(1 - Math.pow(2 * x, 2))) / 2
                    : (Math.sqrt(1 - Math.pow(-2 * x + 2, 2)) + 1) / 2;
        }
    },
    EASE_IN_BACK {
        public double getValueAt(double x) {
            double c1 = 1.70158;
            double c3 = c1 + 1;

            return c3 * x * x * x - c1 * x * x;
        }
    },
    EASE_OUT_BACK {
        public double getValueAt(double x) {
            double c1 = 1.70158;
            double c3 = c1 + 1;

            return 1 + c3 * Math.pow(x - 1, 3) + c1 * Math.pow(x - 1, 2);
        }
    },
    EASE_IN_OUT_BACK {
        public double getValueAt(double x) {
            double c1 = 1.70158;
            double c2 = c1 * 1.525;

            return x < 0.5
                    ? (Math.pow(2 * x, 2) * ((c2 + 1) * 2 * x - c2)) / 2
                    : (Math.pow(2 * x - 2, 2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2;
        }
    },
    EASE_IN_ELASTIC {
        public double getValueAt(double x) {
            double c4 = (2 * Math.PI) / 3;

            return x == 0
                    ? 0
                    : x == 1
                    ? 1
                    : -Math.pow(2, 10 * x - 10) * Math.sin((x * 10 - 10.75) * c4);
        }
    },
    EASE_OUT_ELASTIC {
        public double getValueAt(double x) {
            double c4 = (2 * Math.PI) / 3;

            return x == 0
                    ? 0
                    : x == 1
                    ? 1
                    : Math.pow(2, -10 * x) * Math.sin((x * 10 - 0.75) * c4) + 1;
        }
    },
    EASE_IN_OUT_ELASTIC {
        public double getValueAt(double x) {
            double c5 = (2 * Math.PI) / 4.5;
            double sin = Math.sin((20 * x - 11.125) * c5);

            return x == 0
                    ? 0
                    : x == 1
                    ? 1
                    : x < 0.5
                    ? -(Math.pow(2, 20 * x - 10) * sin) / 2
                    : (Math.pow(2, -20 * x + 10) * sin) / 2 + 1;
        }
    },
    EASE_IN_BOUNCE {
        public double getValueAt(double x) {
            return 1 - EASE_OUT_BOUNCE.getValueAt(1 - x);
        }
    },
    EASE_OUT_BOUNCE {
        public double getValueAt(double x) {
            double n1 = 7.5625;
            double d1 = 2.75;

            if (x < 1 / d1) return n1 * x * x;
            if (x < 2 / d1) return n1 * (x -= 1.5 / d1) * x + 0.75;
            if (x < 2.5 / d1) return n1 * (x -= 2.25 / d1) * x + 0.9375;
            return n1 * (x -= 2.625 / d1) * x + 0.984375;
        }
    },
    EASE_IN_OUT_BOUNCE {
        public double getValueAt(double x) {
            return x < 0.5
                    ? (1 - EASE_OUT_BOUNCE.getValueAt(1 - 2 * x)) / 2
                    : (1 + EASE_OUT_BOUNCE.getValueAt(2 * x - 1)) / 2;
        }
    };
}