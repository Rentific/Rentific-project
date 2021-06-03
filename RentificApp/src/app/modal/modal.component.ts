import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalService2 } from './modal.service';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {
  @Input() title: string;
  @Input() text: string;
  
  constructor(public activeModal: NgbActiveModal, public modalService: ModalService2) { }

  ngOnInit(): void {
  }

  continueButton() {
    this.modalService.ok();
  }
}
