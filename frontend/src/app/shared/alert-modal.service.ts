import { Injectable } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap/modal';
import { AlertModalComponent } from './alert-modal/alert-modal.component';

@Injectable({
  providedIn: 'root'
})
export class AlertModalService {

  constructor(
    private modalService: BsModalService
  ) { }

  showAlert(message: string, type: string) {
    const bsModalRef = this.modalService.show(AlertModalComponent);
    bsModalRef.content.type = type;
    bsModalRef.content.message = message;
  }

  showAlertDanger(message: string){
    this.showAlert(message, 'danger');
  }

  showAlertSuccess(message: string){
    this.showAlert(message, 'success');
  }

}
