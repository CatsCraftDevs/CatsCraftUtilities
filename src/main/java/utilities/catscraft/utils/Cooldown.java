package utilities.catscraft.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Cooldown {
    private static final HashMap<String, Data> COOLDOWN_MAP;

    static {
        COOLDOWN_MAP = new HashMap<>();
    }

    /**
     * If the key exists in the map, and the cooldown hasn't expired, return true and call the consumer with the amount of
     * seconds left. Otherwise, remove the key from the map and return false
     *
     * @param key The key to check for.
     * @param consumer The consumer that will be called if the cooldown is still active (will be in seconds, so use TimeUnit to convert it)
     * @return A boolean value.
     */
    public static boolean hasCooldown(String key, Consumer<Long> consumer) {
        if (!COOLDOWN_MAP.containsKey(key)) return false;

        Data data = COOLDOWN_MAP.get(key);
        long secondsLeft = data.timestamp() / 1000L + data.unit.toSeconds(data.waitTime) - System.currentTimeMillis() / 1000L;

        if (secondsLeft > 0L) {
            consumer.accept(secondsLeft);
            return true;
        }

        COOLDOWN_MAP.remove(key);
        return false;
    }

    /**
     * If the player has a cooldown, it will return true and run the consumer with the remaining time in milliseconds
     *
     * @param player The player to check the cooldown for.
     * @param consumer The consumer that will be called if the cooldown is still active (will be in seconds, so use TimeUnit to convert it)
     * @return A boolean value.
     */
    public static boolean hasCooldown(Player player, Consumer<Long> consumer) {
        return hasCooldown(player.getName(), consumer);
    }

    /**
     * If the cooldown is not expired, it will return false and set the cooldown to the current time. If the cooldown is
     * expired, it will return true and set the cooldown to the current time
     *
     * @param clazz The class that you want to check the cooldown for.
     * @param consumer The consumer that will be called if the cooldown is still active (will be in seconds, so use TimeUnit to convert it)
     * @return A boolean value.
     */
    public static boolean hasCooldown(Class<?> clazz, Consumer<Long> consumer) {
        return hasCooldown(clazz.getSimpleName(), consumer);
    }

    /**
     * Activate a cooldown for a given key, with a given wait time and time unit.
     *
     * @param key The key to identify the cooldown.
     * @param waitTime The amount of time to wait before the cooldown is over.
     * @param unit The unit of time to wait.
     */
    public static void activateCooldown(String key, int waitTime, TimeUnit unit) {
        COOLDOWN_MAP.put(key, new Data(System.currentTimeMillis(), waitTime, unit));
    }

    /**
     * Activates a cooldown for the player with the given name, for the given amount of time, in the given unit of time.
     *
     * @param player The player you want to activate the cooldown for.
     * @param waitTime The amount of time to wait before the cooldown is over.
     * @param unit The unit of time you want to use.
     */
    public static void activateCooldown(Player player, int waitTime, TimeUnit unit) {
        activateCooldown(player.getName(), waitTime, unit);
    }

    /**
     * "Activate a cooldown for the given class with the given wait time."
     *
     * The first parameter is the class that we want to activate a cooldown for. The second parameter is the wait time, and
     * the third parameter is the unit of time that the wait time is in
     *
     * @param clazz The class that you want to activate the cooldown for.
     * @param waitTime The amount of time to wait before the cooldown is over.
     * @param unit The unit of time to wait.
     */
    public static void activateCooldown(Class<?> clazz, int waitTime, TimeUnit unit) {
        activateCooldown(clazz.getSimpleName(), waitTime, unit);
    }

    public static final class Data {
        private final long timestamp;
        private final int waitTime;
        private final TimeUnit unit;

        public Data(long timestamp, int waitTime, TimeUnit unit) {
            this.timestamp = timestamp;
            this.waitTime = waitTime;
            this.unit = unit;
        }

        public long timestamp() {
            return timestamp;
        }

        public int waitTime() {
            return waitTime;
        }

        public TimeUnit unit() {
            return unit;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Data) obj;
            return this.timestamp == that.timestamp &&
                    this.waitTime == that.waitTime &&
                    Objects.equals(this.unit, that.unit);
        }

        @Override
        public int hashCode() {
            return Objects.hash(timestamp, waitTime, unit);
        }

        @Override
        public String toString() {
            return "Data[" +
                    "timestamp=" + timestamp + ", " +
                    "waitTime=" + waitTime + ", " +
                    "unit=" + unit + ']';
        }

    }
}
