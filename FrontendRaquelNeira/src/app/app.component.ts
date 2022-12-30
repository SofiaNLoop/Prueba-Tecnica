import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/service/user.service';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/model/User';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  
  title = 'PruebaDesarrolladorBackend';

  userAddForm!: FormGroup;
  userUpdForm!: FormGroup;
  listUsers!: User[];


  constructor(
    public fb: FormBuilder,
    public userSrv: UserService,
    public modal: NgbModal
  ){
    this.userAddForm = this.fb.group({
      identificacion: ['', Validators.required],
      nombre: ['', Validators.required],
      telefono: ['', Validators.required]
    })

    this.userUpdForm = this.fb.group({
      uIdentificacion: ['', Validators.required],
      uNombre: ['', Validators.required],
      uTelefono: ['', Validators.required]
    })
    
  }

    ngOnInit(): void{
      this.consultUsers();
    }

    consultUsers (){
      this.userSrv.listUsers()
      .subscribe( data => {
        this.listUsers = data;
      }), (error: any) => {
        console.log(error);
      }

    }

    addUser(){
      const newUser: User = {
        identificacion: this.userAddForm .get('identificacion')?.value,
        nombre: this.userAddForm .get('nombre')?.value,
        telefono: this.userAddForm .get('telefono')?.value,
      }
      this.userSrv.createUser(newUser)
      .subscribe( data => {
        Swal.fire({
          icon: 'success',
          title: `${data.status}`,
          text: `${data.msg}`
        }).then((result) => {
          if (result.isConfirmed) {
            this.ngOnInit();
          }
        })
      }, error => {
        console.log(error);
        Swal.fire({
          icon: 'error',
          title: 'Error!',
          text: `${error.error}`,
          showCloseButton: true,
          confirmButtonText:'Vale!'
        })
      } ) 

    }

    fillFields(userFields: User){
      console.log("prueba");
      this.userUpdForm.get('uIdentificacion')?.setValue(userFields.identificacion);
      this.userUpdForm.get('uNombre')?.setValue(userFields.nombre);
      this.userUpdForm.get('uTelefono')?.setValue(userFields.telefono);
    }


    updUser(){
      const updUser: User = {
        identificacion: this.userUpdForm.get('uIdentificacion')?.value,
        nombre: this.userUpdForm.get('uNombre')?.value,
        telefono: this.userUpdForm.get('uTelefono')?.value,
      }
      this.userSrv.updateUser(updUser)
      .subscribe( data => {
        Swal.fire({
          icon: 'success',
          title: `${data.status}`,
          text: `${data.msg}`
        }).then((result) => {
          if (result.isConfirmed) {
            this.ngOnInit();
          }
        })
      }, error => {
        Swal.fire({
          icon: 'error',
          title: 'Error!',
          text: `${error.error}`,
          showCloseButton: true,
          confirmButtonText:'Vale!'
        })
      } ) 
    }

    delUser(id: Number){

      Swal.fire({
        title: '¿Está seguro de eliminar este usuario?',
        text: "No podrá revertirlo.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, ¡Eliminar!'
      }).then((result) => {
        if (result.isConfirmed) {
          this.userSrv.deleteUser(id)
          .subscribe( data => {
            Swal.fire({
              icon: 'success',
              title: `${data.status}`,
              text: `${data.msg}`
            }).then((result) => {
              if (result.isConfirmed) {
                this.ngOnInit();
              }
            })
          }, error => {
            Swal.fire({
              icon: 'error',
              title: 'Error!',
              text: `${error.error}`,
              showCloseButton: true,
              confirmButtonText:'Vale!'
            })
          })
          
        }
        
      })

    }

}
