import { ApplicationConfig, APP_INITIALIZER, inject } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { provideClientHydration } from '@angular/platform-browser';

import { routes } from './app.routes';
import { APP_API_TOKEN, AppConfig } from './core/config/app-api.token';

function getApiUrl(): string {
    return '/api/creditos';
}

function initApp() {
    const config: AppConfig = {
        apiUrl: getApiUrl()
    };
    (window as any).__appConfig = config;
    return Promise.resolve();
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(withFetch()),
    provideClientHydration(),
    {
        provide: APP_API_TOKEN,
        useValue: {
            API: getApiUrl()
        }
    },
    {
        provide: APP_INITIALIZER,
        useFactory: () => initApp,
        multi: true
    }
  ]
};

