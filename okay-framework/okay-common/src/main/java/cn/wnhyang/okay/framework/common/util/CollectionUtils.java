package cn.wnhyang.okay.framework.common.util;

import cn.hutool.core.collection.CollUtil;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author wnhyang
 * @date 2023/8/3
 **/
public class CollectionUtils {

    public static <T, U> List<U> convertList(Collection<T> from, Function<T, U> func) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().map(func).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static <T, U> Set<U> convertSet(Collection<T> from, Function<T, U> func) {
        if (CollUtil.isEmpty(from)) {
            return new HashSet<>();
        }
        return from.stream().map(func).filter(Objects::nonNull).collect(Collectors.toSet());
    }
}
