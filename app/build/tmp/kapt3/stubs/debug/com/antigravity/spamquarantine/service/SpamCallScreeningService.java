package com.antigravity.spamquarantine.service;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/antigravity/spamquarantine/service/SpamCallScreeningService;", "Landroid/telecom/CallScreeningService;", "()V", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "isContact", "", "context", "Landroid/content/Context;", "number", "", "onScreenCall", "", "callDetails", "Landroid/telecom/Call$Details;", "app_debug"})
public final class SpamCallScreeningService extends android.telecom.CallScreeningService {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    
    public SpamCallScreeningService() {
        super();
    }
    
    @java.lang.Override()
    public void onScreenCall(@org.jetbrains.annotations.NotNull()
    android.telecom.Call.Details callDetails) {
    }
    
    /**
     * Revisa si el número de teléfono existe en los contactos locales del dispositivo.
     */
    private final boolean isContact(android.content.Context context, java.lang.String number) {
        return false;
    }
}