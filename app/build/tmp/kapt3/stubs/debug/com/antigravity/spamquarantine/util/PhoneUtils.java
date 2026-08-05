package com.antigravity.spamquarantine.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00050\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006J\u0010\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u0006\u00a8\u0006\r"}, d2 = {"Lcom/antigravity/spamquarantine/util/PhoneUtils;", "", "()V", "getDefaultChileSpamPatterns", "", "Lkotlin/Pair;", "", "matchesRegexPattern", "", "e164Number", "regexPattern", "normalizeChilePhoneNumber", "rawNumber", "app_debug"})
public final class PhoneUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.antigravity.spamquarantine.util.PhoneUtils INSTANCE = null;
    
    private PhoneUtils() {
        super();
    }
    
    /**
     * Normaliza un número telefónico chileno a formato E.164 (+56XXXXXXXXX).
     * Ejemplos:
     * "600 716 4000" -> "+566007164000"
     * "(80) 902 8449" -> "+56809028449"
     * "+56 9 2882 8730" -> "+56928828730"
     * "+56 600 338 0002" -> "+566003380002"
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String normalizeChilePhoneNumber(@org.jetbrains.annotations.Nullable()
    java.lang.String rawNumber) {
        return null;
    }
    
    /**
     * Verifica si un número formateado en E.164 coincide con una expresión regular dada.
     */
    public final boolean matchesRegexPattern(@org.jetbrains.annotations.NotNull()
    java.lang.String e164Number, @org.jetbrains.annotations.NotNull()
    java.lang.String regexPattern) {
        return false;
    }
    
    /**
     * Lista de patrones de expresiones regulares predeterminados para combatir el spam telefónico en Chile.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> getDefaultChileSpamPatterns() {
        return null;
    }
}