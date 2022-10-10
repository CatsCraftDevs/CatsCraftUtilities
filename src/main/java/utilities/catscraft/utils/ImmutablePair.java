package utilities.catscraft.utils;

import java.io.Serializable;
import java.util.Map;

public final class ImmutablePair<L, R> implements Map.Entry<L, R>, Serializable {
    private static final ImmutablePair NULL = of(null, null);
    private static final long serialVersionUID = 4954918890077093841L;

    public static <L, R> ImmutablePair<L, R> nullPair() {
        return NULL;
    }

    public final L key;
    public final R value;

    public static <L, R> ImmutablePair<L, R> of(final L key, final R value) {
        return new ImmutablePair<>(key, value);
    }

    public ImmutablePair(final L key, final R value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public L getKey() {
        return key;
    }

    @Override
    public R getValue() {
        return value;
    }

    @Override
    public R setValue(final R value) {
        throw new UnsupportedOperationException();
    }
}
