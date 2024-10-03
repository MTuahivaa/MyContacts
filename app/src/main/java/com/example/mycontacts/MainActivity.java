package com.example.mycontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewThumbnail;
    private List<Integer> listOfThumbnails = new ArrayList<>();
    private List<Contact> listOfContacts = new ArrayList<>(); // Liste de contacts
    private int currentThumbnailIndex = 0;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadAddressBook();
        imageViewThumbnail = findViewById(R.id.imageViewThumbnail);
        initThumbnailList();
        displayThumbnail(currentThumbnailIndex);
        displayContactDetails(currentIndex);
        setupContactNumEditText();
    }
    private void initThumbnailList(){
        listOfThumbnails.add(R.drawable.client1);
        listOfThumbnails.add(R.drawable.client2);
        listOfThumbnails.add(R.drawable.client3);
        listOfThumbnails.add(R.drawable.client4);
        listOfThumbnails.add(R.drawable.client5);
        listOfThumbnails.add(R.drawable.client6);
        listOfThumbnails.add(R.drawable.client7);
    }
    private void displayThumbnail(int index) {
        int thumbnailResId = listOfThumbnails.get(index);
        imageViewThumbnail.setImageResource(thumbnailResId);
    }
    public void buttonPreviousImage(View view) {
        if (currentThumbnailIndex > 0) {
            currentThumbnailIndex--;
        } else {
            currentThumbnailIndex = listOfThumbnails.size() - 1;
        }
        displayThumbnail(currentThumbnailIndex);
    }
    public void buttonNextImage(View view) {
        if (currentThumbnailIndex < listOfThumbnails.size() - 1) {
            currentThumbnailIndex++;
        } else {
            currentThumbnailIndex = 0;
        }
        displayThumbnail(currentThumbnailIndex);
    }
    private void loadAddressBook() {
        try {
            FileInputStream fileInputStream = openFileInput("carnet.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

            String line;
            while ((line = br.readLine()) != null) {
                String[] contactData = line.split(";");

                if (contactData.length == 10) { // Vérifiez si la ligne contient toutes les informations nécessaires
                    int contactNumber = Integer.parseInt(contactData[0].trim());
                    String firstName = contactData[1].trim();
                    String lastName = contactData[2].trim();
                    String phoneNumber = contactData[3].trim();
                    String address = contactData[4].trim();
                    String postalCode = contactData[5].trim();
                    String email = contactData[6].trim();
                    String job = contactData[7].trim();
                    String situation = contactData[8].trim();
                    int thumbnailIndex = Integer.parseInt(contactData[9].trim());

                    Contact contact = new Contact(contactNumber, firstName, lastName, phoneNumber, address, postalCode, email, job, situation, thumbnailIndex);
                    listOfContacts.add(contact); // Ajoutez le contact à la liste
                }
            }
            br.close();
            Toast.makeText(getApplicationContext(), "Carnet chargé avec succès", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Carnet non trouvé", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void displayContactDetails(int index){
        EditText editTextContactNumber = findViewById(R.id.editTextContactNumber);
        EditText editTextFirstName = findViewById(R.id.editTextFirstName);
        EditText editTextLastName = findViewById(R.id.editTextLastName);
        EditText editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        EditText editTextAddress = findViewById(R.id.editTextAddress);
        EditText editTextPostalCode = findViewById(R.id.editTextPostalCode);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextJob = findViewById(R.id.editTextJob);
        EditText editTextSituation = findViewById(R.id.editTextSituation);

        // Set initial contact details (Here, I'm setting details of the first contact as an example)
        if (index >= 0 && index < listOfContacts.size()) {
            Contact firstContact = listOfContacts.get(index);
            editTextContactNumber.setText(String.valueOf(firstContact.getContactNumber()));
            editTextFirstName.setText(firstContact.getFirstName());
            editTextLastName.setText(firstContact.getLastName());
            editTextPhoneNumber.setText(firstContact.getPhoneNumber());
            editTextAddress.setText(firstContact.getAddress());
            editTextPostalCode.setText(firstContact.getPostalCode());
            editTextEmail.setText(firstContact.getEmail());
            editTextJob.setText(firstContact.getJob());
            editTextSituation.setText(firstContact.getSituation());
            displayThumbnail(firstContact.getThumbnailIndex());
        }
    }
    public void buttonFirstClick(View view) {
        currentIndex = 0;
        displayContactDetails(currentIndex); // Affiche les détails du premier contact
    }
    public void buttonNextClick(View view) {
        if (currentIndex < listOfContacts.size() - 1) {
            currentIndex++; // Incrémente l'index actuel
            displayContactDetails(currentIndex); // Affiche les détails du contact suivant
        } else if (currentIndex == listOfContacts.size() - 1 && listOfContacts.size() > 0) {
            currentIndex = 0;
            displayContactDetails(currentIndex);
        }
    }
    public void buttonPreviousClick(View view) {
        if (currentIndex > 0) {
            currentIndex--; // Décrémente l'index actuel
            displayContactDetails(currentIndex); // Affiche les détails du contact précédent
        } else if (currentIndex == 0 && listOfContacts.size() > 0) {
            currentIndex = listOfContacts.size() - 1; // Index du dernier contact
            displayContactDetails(currentIndex); // Affiche les détails du dernier contact
        }
    }
    public void buttonLastClick(View view) {
        currentIndex = listOfContacts.size() - 1;
        displayContactDetails(currentIndex);
    }
    private void setupContactNumEditText() {
        EditText editTextContactNum = findViewById(R.id.editTextContactNum);
        editTextContactNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Ne rien faire ici
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    try {
                        // Convertir le texte en entier
                        int contactNumber = Integer.parseInt(charSequence.toString());

                        // Vérifier si le contact existe dans la liste
                        if (contactNumber > 0 && contactNumber <= listOfContacts.size()) {
                            // Mettre à jour le currentIndex avec le contactNumber - 1 (car l'index commence à 0)
                            currentIndex = contactNumber - 1;

                            // Afficher les détails du contact correspondant
                            displayContactDetails(currentIndex);
                        } else {
                            // Si le numéro ne correspond à aucun contact, définir currentIndex à -1 ou une autre valeur par défaut
                            currentIndex = -1;
                        }
                    } catch (NumberFormatException e) {
                        // Si la conversion échoue, gérer l'exception
                        e.printStackTrace();
                        currentIndex = -1; // Réinitialiser en cas d'erreur
                    }
                } else {
                    currentIndex = -1; // Réinitialiser si le champ est vide
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Ne rien faire ici
            }
        });
    }
    private void searchContactByNumber(String contactNumber) {
        String enteredText = contactNumber;

        // Si le champ de texte n'est pas vide
        if (!enteredText.isEmpty()) {
            int contactNum = Integer.parseInt(enteredText);

            // Parcourir la liste des contacts pour trouver celui qui correspond au numéro entré
            for (int i = 0; i < listOfContacts.size(); i++) {
                if (listOfContacts.get(i).getContactNumber() == contactNum) {
                    displayContactDetails(i); // Afficher les détails du contact correspondant
                    return;
                }
            }
        }
    }
    public void buttonCreateClick(View view){
        clearContactDetails();
        setNewContactNumber();
    }
    private void clearContactDetails() {
        EditText editTextContactNumber = findViewById(R.id.editTextContactNumber);
        EditText editTextFirstName = findViewById(R.id.editTextFirstName);
        EditText editTextLastName = findViewById(R.id.editTextLastName);
        EditText editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        EditText editTextAddress = findViewById(R.id.editTextAddress);
        EditText editTextPostalCode = findViewById(R.id.editTextPostalCode);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextJob = findViewById(R.id.editTextJob);
        EditText editTextSituation = findViewById(R.id.editTextSituation);

        // Effacez le texte dans tous les champs EditText
        editTextContactNumber.setText("");
        editTextFirstName.setText("");
        editTextLastName.setText("");
        editTextPhoneNumber.setText("");
        editTextAddress.setText("");
        editTextPostalCode.setText("");
        editTextEmail.setText("");
        editTextJob.setText("");
        editTextSituation.setText("");
    }
    private void setNewContactNumber() {
        EditText editTextContactNumber = findViewById(R.id.editTextContactNumber);

        if (!listOfContacts.isEmpty()) {
            // Obtenir le dernier contact de la liste
            Contact lastContact = listOfContacts.get(listOfContacts.size() - 1);
            int newContactNumber = lastContact.getContactNumber() + 1; // Incrémentation du dernier numéro

            // Remplir le champ ContactNumber avec le nouveau numéro
            editTextContactNumber.setText(String.valueOf(newContactNumber));
            currentIndex = newContactNumber + 1;
        } else {
            // Si la liste est vide, attribuer le numéro 1 pour le premier contact
            editTextContactNumber.setText("1");
        }

    }
    private void createNewContact(){
        EditText editTextContactNumber = findViewById(R.id.editTextContactNumber);
        EditText editTextFirstName = findViewById(R.id.editTextFirstName);
        EditText editTextLastName = findViewById(R.id.editTextLastName);
        EditText editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        EditText editTextAddress = findViewById(R.id.editTextAddress);
        EditText editTextPostalCode = findViewById(R.id.editTextPostalCode);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextJob = findViewById(R.id.editTextJob);
        EditText editTextSituation = findViewById(R.id.editTextSituation);

        // Obtenir les informations des champs EditText pour créer un nouveau contact
        int contactNumber = Integer.parseInt(editTextContactNumber.getText().toString());
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();
        String address = editTextAddress.getText().toString();
        String postalCode = editTextPostalCode.getText().toString();
        String email = editTextEmail.getText().toString();
        String job = editTextJob.getText().toString();
        String situation = editTextSituation.getText().toString();
        int thumbnailIndex = currentThumbnailIndex;

        // Créer un nouveau contact avec les informations des champs EditText
        Contact newContact = new Contact(contactNumber, lastName, firstName, phoneNumber, address, postalCode, email, job, situation, currentThumbnailIndex);
        listOfContacts.add(newContact);
    }
    public void buttonDeleteClick(View view){
        if (!listOfContacts.isEmpty()) {
            listOfContacts.remove(currentIndex); // Supprime le contact actuellement affiché

            if (!listOfContacts.isEmpty()) {
                if (currentIndex == listOfContacts.size()) {
                    // Si l'index actuel est supérieur à la taille de la liste, affiche le dernier contact
                    currentIndex = listOfContacts.size() - 1;
                }
                displayContactDetails(currentIndex); // Affiche le contact après suppression
            } else {
                clearContactDetails(); // Réinitialise les champs s'il n'y a plus de contacts dans la liste
            }
        }
    }
    public void buttonSaveClick(View view) {
        saveAddressBook();
    }
    private void saveAddressBook() {
        EditText editTextFirstName = findViewById(R.id.editTextFirstName);
        EditText editTextLastName = findViewById(R.id.editTextLastName);
        EditText editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        EditText editTextAddress = findViewById(R.id.editTextAddress);
        EditText editTextPostalCode = findViewById(R.id.editTextPostalCode);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextJob = findViewById(R.id.editTextJob);
        EditText editTextSituation = findViewById(R.id.editTextSituation);

        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();
        String address = editTextAddress.getText().toString();
        String postalCode = editTextPostalCode.getText().toString();
        String email = editTextEmail.getText().toString();
        String job = editTextJob.getText().toString();
        String situation = editTextSituation.getText().toString();

        if (currentIndex >= listOfContacts.size()) {
            createNewContact();
        } else {
            Contact currentContact = listOfContacts.get(currentIndex);
            currentContact.setFirstName(firstName);
            currentContact.setLastName(lastName);
            currentContact.setPhoneNumber(phoneNumber);
            currentContact.setAddress(address);
            currentContact.setPostalCode(postalCode);
            currentContact.setEmail(email);
            currentContact.setJob(job);
            currentContact.setSituation(situation);
            currentContact.setThumbnailIndex(currentThumbnailIndex);
        }

        try {
            FileOutputStream fileOutputStream = openFileOutput("carnet.txt", Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            for (Contact contact : listOfContacts) {
                String contactLine = contact.getContactNumber() + ";" +
                                    contact.getFirstName() + ";" +
                                    contact.getLastName() + ";" +
                                    contact.getPhoneNumber() + ";" +
                                    contact.getAddress() + ";" +
                                    contact.getPostalCode() + ";" +
                                    contact.getEmail() + ";" +
                                    contact.getJob() + ";" +
                                    contact.getSituation() + ";" +
                                    contact.getThumbnailIndex() + "\n";

                outputStreamWriter.write(contactLine);
            }

            outputStreamWriter.flush();
            outputStreamWriter.close();
            // Affiche un message indiquant que la sauvegarde est réussie (facultatif)
            Toast.makeText(getApplicationContext(), "Carnet enregistré avec succès", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            // Affiche un message d'erreur en cas de problème lors de la sauvegarde (facultatif)
            Toast.makeText(getApplicationContext(), "Erreur lors de l'enregistrement du carnet", Toast.LENGTH_SHORT).show();
        }
    }
}