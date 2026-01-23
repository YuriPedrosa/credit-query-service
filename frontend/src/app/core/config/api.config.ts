export const APP_API = {
    API: (window as any).__env?.API_URL || '/api/creditos'
};

export function loadEnv() {
    return Promise.resolve();
}

