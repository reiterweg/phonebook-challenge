import { enableProdMode } from '@angular/core';
import { platformBrowser } from '@angular/platform-browser';

import { AppModuleNgFactory } from './app/app.module.ngfactory';

import './assets/scss/main.scss';

enableProdMode();

platformBrowser().bootstrapModuleFactory(AppModuleNgFactory);