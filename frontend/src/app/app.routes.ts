import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'creditos',
        loadChildren: () =>
            import('./features/creditos/creditos.routes')
                .then(m => m.CREDITOS_ROUTES)
    },
    { path: '', redirectTo: 'creditos', pathMatch: 'full' },
    { path: '**', redirectTo: 'creditos' }
];
