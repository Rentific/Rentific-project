import {EventEmitter, Injectable, Output} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class ModalService2 {
  @Output() continue: EventEmitter<any> = new EventEmitter();
  constructor() {}
    
  getModalContinueEmitter() {
    return this.continue;
  }

  ok() {
    this.continue.emit();
  }
}