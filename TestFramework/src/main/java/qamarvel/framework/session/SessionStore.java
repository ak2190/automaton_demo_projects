package qamarvel.framework.session;

public class SessionStore {

    private static AuthSession authSession;

    private SessionStore() {}

    public static synchronized void save(AuthSession session) {
        authSession = session;
    }

    public static synchronized AuthSession get() {
        if (authSession == null) {
            throw new IllegalStateException("Auth session not initialized");
        }
        return authSession;
    }

    public static synchronized boolean isInitialized() {
        return authSession != null;
    }
}
