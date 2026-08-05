package com.antigravity.spamquarantine.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/antigravity/spamquarantine/data/db/RuleDao;", "", "deleteRule", "", "rule", "Lcom/antigravity/spamquarantine/data/model/RuleEntity;", "(Lcom/antigravity/spamquarantine/data/model/RuleEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveRules", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllRules", "getRuleCount", "", "insertRule", "", "app_debug"})
@androidx.room.Dao()
public abstract interface RuleDao {
    
    @androidx.room.Query(value = "SELECT * FROM spam_rules WHERE isActive = 1 ORDER BY id DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getActiveRules(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.antigravity.spamquarantine.data.model.RuleEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM spam_rules ORDER BY id DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllRules(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.antigravity.spamquarantine.data.model.RuleEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertRule(@org.jetbrains.annotations.NotNull()
    com.antigravity.spamquarantine.data.model.RuleEntity rule, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteRule(@org.jetbrains.annotations.NotNull()
    com.antigravity.spamquarantine.data.model.RuleEntity rule, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM spam_rules")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRuleCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}