package librarysystem.util;

/**
 * Created by Rory on 11/21/2016.
 */
public class TimeUtil {

    private static String second = "second";
    private static String seconds = "seconds";
    private static String minute = "minute";
    private static String minutes = "minutes";
    private static String hour = "hour";
    private static String hours = "hours";
    private static String day = "day";
    private static String days = "days";
    private static String week = "week";
    private static String weeks = "weeks";
    private static String month = "month";
    private static String months = "months";
    private static String year = "year";
    private static String years = "years";
    private static String and = "and";

    public static void setSecond(String second) {
        TimeUtil.second = second;
    }

    public static void setSeconds(String seconds) {
        TimeUtil.seconds = seconds;
    }

    public static void setMinute(String minute) {
        TimeUtil.minute = minute;
    }

    public static void setMinutes(String minutes) {
        TimeUtil.minutes = minutes;
    }

    public static void setHour(String hour) {
        TimeUtil.hour = hour;
    }

    public static void setHours(String hours) {
        TimeUtil.hours = hours;
    }

    public static void setDay(String day) {
        TimeUtil.day = day;
    }

    public static void setDays(String days) {
        TimeUtil.days = days;
    }

    public static void setWeek(String week) {
        TimeUtil.week = week;
    }

    public static void setWeeks(String weeks) {
        TimeUtil.weeks = weeks;
    }

    public static void setMonth(String month) {
        TimeUtil.month = month;
    }

    public static void setMonths(String months) {
        TimeUtil.months = months;
    }

    public static void setYear(String year) {
        TimeUtil.year = year;
    }

    public static void setYears(String years) {
        TimeUtil.years = years;
    }

    public static void setAnd(String and) {
        TimeUtil.and = and;
    }

    public static String formatTime(long seconds) {
        String timeText = "";
        seconds += 1L;
        if (seconds % (60 * 60 * 24 * 365) >= 0) {
            int timecalc = (int) Math.floor(seconds / (60 * 60 * 24 * 365));
            seconds = seconds % (60 * 60 * 24 * 365);
            if (timecalc != 0) {
                if (timecalc == 1) {
                    timeText += timecalc + " " + TimeUtil.year + ", ";
                } else {
                    timeText += timecalc + " " + TimeUtil.years + ", ";
                }
            }
        }

        if (seconds % (30 * 24 * 60 * 60) >= 0) {
            int timecalc = (int) Math.floor(seconds / (30 * 24 * 60 * 60));
            seconds = seconds % (30 * 24 * 60 * 60);
            if (timecalc != 0) {
                if (timecalc == 1) {
                    timeText += timecalc + " " + TimeUtil.month + ", ";
                } else {
                    timeText +=  timecalc + " " + TimeUtil.months + ", ";
                }
            }
        }

        if (seconds % (60 * 60 * 24 * 7) >= 0) {
            int timecalc = (int) Math.floor(seconds / (60 * 60 * 24 * 7));
            seconds = seconds % (60 * 60 * 24 * 7);
            if (timecalc != 0) {
                if (timecalc == 1) {
                    timeText += timecalc + " " + TimeUtil.week + ", ";
                } else {
                    timeText += timecalc + " " + TimeUtil.weeks + ", ";
                }
            }
        }

        if (seconds % (60 * 60 * 24) >= 0) {
            int timecalc = (int) Math.floor(seconds / (60 * 60 * 24));
            seconds = seconds % (60 * 60 * 24);
            if (timecalc != 0) {
                if (timecalc == 1) {
                    timeText += timecalc + " " + TimeUtil.day + ", ";
                } else {
                    timeText += timecalc + " " + TimeUtil.days + ", ";
                }
            }
        }

        if (seconds % (60 * 60) >= 0) {
            int timecalc = (int) Math.floor(seconds / (60 * 60));
            seconds = seconds % (60 * 60);
            if (timecalc != 0) {
                if (timecalc == 1) {
                    timeText += timecalc + " " + TimeUtil.hour + ", ";
                } else {
                    timeText += timecalc + " " + TimeUtil.hours + ", ";
                }
            }
        }

        if (seconds % 60 >= 0) {
            int timecalc = (int) Math.floor(seconds / (60));
            seconds = seconds % (60);
            if (timecalc != 0) {
                if (timecalc == 1) {
                    timeText += timecalc + " " + TimeUtil.minute + ", ";
                } else {
                    timeText += timecalc + " " + TimeUtil.minutes + ", ";
                }
            }
        }

        if (seconds > 0) {
            if (seconds == 1) {
                timeText += seconds + " " + TimeUtil.second + ", ";
            } else {
                timeText += seconds + " " + TimeUtil.seconds + ", ";
            }
        }
        if (timeText.length() > 0) {
            timeText = timeText.substring(0, timeText.length() - 2);
            int lastComma = timeText.lastIndexOf(",");
            if (lastComma != -1) {
                timeText = timeText.substring(0, lastComma) + " " + TimeUtil.and + " " + timeText.substring(lastComma + 2, timeText.length());
            }
        } else {
            timeText = "0 " + TimeUtil.seconds;
        }
        return timeText;
    }
    
	public static String getNiceTime(Long seconds) {
		int days = TimeUtil.getDays(seconds);
		if (days == 1) {
			int hours = (int) Math.ceil((double) seconds / (60 * 60L));
			if (hours == 1) {
				int minutes = (int) Math.ceil((double) seconds / 60L);
				if (minutes == 1) {
					return minutes + " " + TimeUtil.minute;
				} else {
					return minutes + " " + TimeUtil.minutes;
				}
			} else {
				return hours + " " + TimeUtil.hours;
			}
		} else {
			return days + " " + TimeUtil.days;
		}
	}
	
	public static int getDays(Long seconds) {
		return (int) Math.ceil((double) seconds / (60L * 60L * 24L));
	}

}
