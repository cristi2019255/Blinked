package com.example.blinked;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class FereastraAutoexcludere extends AppCompatActivity {
    private static final int STORAGE_CODE = 1000;
    private RadioGroup rg1;
    private RadioGroup rg2;
    private RadioGroup rg3;
    private RadioGroup rg4;
    private RadioGroup rg5;
    private RadioGroup rg6;
    private RadioGroup rg7;
    private RadioGroup rg8;
    private RadioGroup rg9;
    private RadioGroup rg10;
    private RadioGroup rg11;
    private RadioGroup rg12 ;
    private RadioGroup rg13 ;
    private RadioGroup rg14 ;
    private RadioGroup rg15 ;
    private RadioGroup rg16 ;
    private RadioGroup rg17;
    private RadioGroup rg18 ;
    private RadioGroup rg19 ;
    private RadioGroup rg22 ;
    private RadioGroup rg24 ;
    private RadioGroup rg25 ;
    private RadioGroup rg26 ;
    private RadioGroup rg27 ;
    private RadioGroup rg28 ;
    private RadioGroup rg29 ;
    private RadioGroup rg30 ;
    private RadioGroup rg31 ;
    private RadioGroup rg32 ;
    private RadioGroup rg33 ;
    private RadioGroup rg36 ;
    private RadioGroup rg37 ;
    private RadioButton selectat1;
    private RadioButton selectat2 ;
    private RadioButton selectat3 ;
    private RadioButton selectat4;
    private RadioButton selectat5 ;
    private RadioButton selectat6 ;
    private RadioButton selectat7 ;
    private RadioButton selectat8 ;
    private RadioButton selectat9;
    private RadioButton selectat10;
    private RadioButton selectat11 ;
    private RadioButton selectat12;
    private RadioButton selectat13;
    private RadioButton selectat14 ;
    private RadioButton selectat15 ;
    private RadioButton selectat16;
    private RadioButton selectat17 ;
    private RadioButton selectat18 ;
    private RadioButton selectat19;
    private RadioButton selectat20 ;
    private RadioButton selectat21 ;
    private RadioButton selectat22 ;
    private RadioButton selectat23 ;
    private RadioButton selectat24 ;
    private RadioButton selectat25 ;
    private RadioButton selectat26 ;
    private RadioButton selectat27;
    private RadioButton selectat28 ;
    private RadioButton selectat29;
    private RadioButton selectat30 ;
    private RadioButton selectat31 ;
    private RadioButton selectat32;
    private EditText editText2;
    private EditText editText3 ;
    private EditText editText4;
    private EditText editText5 ;
    private EditText editText6;
    private static int years_old;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fereastra_autoexcludere);
        FirebaseUser fuser=FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                String [] dates=user.getBirthDate().split("/");
                int years=0;
                try {
                    int zz=Integer.parseInt(dates[0]);
                    int mm=Integer.parseInt(dates[1]);
                    int yy=Integer.parseInt(dates[2]);
                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    String [] datenow=currentDate.split("-");
                    int zzn=Integer.parseInt(datenow[0]);
                    int mmn=Integer.parseInt(datenow[1]);
                    int yyn=Integer.parseInt(datenow[2]);
                    years=yyn-yy-1;
                    if (mm<mmn){
                        years++;
                    }
                    if ((mm==mmn)&&(zzn>zz)){
                        years++;
                    }
                    years_old=years;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        Button button = (Button) findViewById(R.id.butonInregistreaza);
        rg1 = (RadioGroup) findViewById(R.id.rg1);
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        rg3 = (RadioGroup) findViewById(R.id.rg3);
        rg4 = (RadioGroup) findViewById(R.id.rg4);
        rg5 = (RadioGroup) findViewById(R.id.rg5);
        rg6 = (RadioGroup) findViewById(R.id.rg6);
        rg7 = (RadioGroup) findViewById(R.id.rg7);
        rg8 = (RadioGroup) findViewById(R.id.rg8);
        rg9 = (RadioGroup) findViewById(R.id.rg9);
        rg10 = (RadioGroup) findViewById(R.id.rg10);
        rg11 = (RadioGroup) findViewById(R.id.rg11);
        rg12 = (RadioGroup) findViewById(R.id.rg12);
        rg13 = (RadioGroup) findViewById(R.id.rg13);
        rg14 = (RadioGroup) findViewById(R.id.rg14);
        rg15 = (RadioGroup) findViewById(R.id.rg15);
        rg16 = (RadioGroup) findViewById(R.id.rg16);
        rg17 = (RadioGroup) findViewById(R.id.rg17);
        rg18 = (RadioGroup) findViewById(R.id.rg18);
        rg19 = (RadioGroup) findViewById(R.id.rg19);
        rg22 = (RadioGroup) findViewById(R.id.rg22);
        rg24 = (RadioGroup) findViewById(R.id.rg24);
        rg25 = (RadioGroup) findViewById(R.id.rg25);
        rg26 = (RadioGroup) findViewById(R.id.rg26);
        rg27 = (RadioGroup) findViewById(R.id.rg27);
        rg28 = (RadioGroup) findViewById(R.id.rg28);
        rg29 = (RadioGroup) findViewById(R.id.rg29);
        rg30 = (RadioGroup) findViewById(R.id.rg30);
        rg31 = (RadioGroup) findViewById(R.id.rg31);
        rg32 = (RadioGroup) findViewById(R.id.rg32);
        rg33 = (RadioGroup) findViewById(R.id.rg33);
        rg36 = (RadioGroup) findViewById(R.id.rg36);
        rg37 = (RadioGroup) findViewById(R.id.rg37);
        selectat1 = (RadioButton) findViewById(rg1.getCheckedRadioButtonId());
        selectat2 = (RadioButton) findViewById(rg2.getCheckedRadioButtonId());
        selectat3 = (RadioButton) findViewById(rg3.getCheckedRadioButtonId());
        selectat4 = (RadioButton) findViewById(rg4.getCheckedRadioButtonId());
        selectat5 = (RadioButton) findViewById(rg5.getCheckedRadioButtonId());
        selectat6 = (RadioButton) findViewById(rg6.getCheckedRadioButtonId());
        selectat7 = (RadioButton) findViewById(rg7.getCheckedRadioButtonId());
        selectat8 = (RadioButton) findViewById(rg8.getCheckedRadioButtonId());
        selectat9 = (RadioButton) findViewById(rg9.getCheckedRadioButtonId());
        selectat10 = (RadioButton) findViewById(rg10.getCheckedRadioButtonId());
        selectat11 = (RadioButton) findViewById(rg11.getCheckedRadioButtonId());
        selectat12 = (RadioButton) findViewById(rg12.getCheckedRadioButtonId());
        selectat13 = (RadioButton) findViewById(rg13.getCheckedRadioButtonId());
        selectat14 = (RadioButton) findViewById(rg14.getCheckedRadioButtonId());
        selectat15 = (RadioButton) findViewById(rg15.getCheckedRadioButtonId());
        selectat16 = (RadioButton) findViewById(rg16.getCheckedRadioButtonId());
        selectat17 = (RadioButton) findViewById(rg17.getCheckedRadioButtonId());
        selectat18 = (RadioButton) findViewById(rg18.getCheckedRadioButtonId());
        selectat19 = (RadioButton) findViewById(rg19.getCheckedRadioButtonId());
        selectat20 = (RadioButton) findViewById(rg22.getCheckedRadioButtonId());
        selectat21 = (RadioButton) findViewById(rg24.getCheckedRadioButtonId());
        selectat22 = (RadioButton) findViewById(rg25.getCheckedRadioButtonId());
        selectat23 = (RadioButton) findViewById(rg26.getCheckedRadioButtonId());
        selectat24 = (RadioButton) findViewById(rg27.getCheckedRadioButtonId());
        selectat25 = (RadioButton) findViewById(rg28.getCheckedRadioButtonId());
        selectat26 = (RadioButton) findViewById(rg29.getCheckedRadioButtonId());
        selectat27 = (RadioButton) findViewById(rg30.getCheckedRadioButtonId());
        selectat28 = (RadioButton) findViewById(rg31.getCheckedRadioButtonId());
        selectat29 = (RadioButton) findViewById(rg32.getCheckedRadioButtonId());
        selectat30 = (RadioButton) findViewById(rg33.getCheckedRadioButtonId());
        selectat31 = (RadioButton) findViewById(rg36.getCheckedRadioButtonId());
        selectat32 = (RadioButton) findViewById(rg37.getCheckedRadioButtonId());
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        editText6 = (EditText) findViewById(R.id.editText6);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, STORAGE_CODE);
                    }
                    else {
                        savePdf();
                        startActivity(new Intent(FereastraAutoexcludere.this, Profile.class));
                    }
                }
            }
        });
    }

    private void savePdf() {
        Document document = new Document();
        String fileName = "donare sange";
        String filePath = Environment.getExternalStorageDirectory() + "/" + fileName + ".pdf";
        try{
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            String text = "ANUL: "+ Calendar.getInstance().get(Calendar.YEAR)+" NUME/PRENUME: "+ firebaseUser.getDisplayName() + " VÂRSTA: "+years_old+"\n\n" +
                    "                  CHESTIONAR PENTRU DONATORUL DE SÂNGE\n\n"+ "Convorbirea în cadrul consultatiei medicale este acoperita de secretul medical.\n" +
                    "Precautiunile sunt luate in scopul securitatii medicale, atat pentru donator cat si pentru\n\n" +
                    "primitorul de sânge.\n"+ "1. Considerati ca aveti o stare buna de sanatate? "+selectat1.getText()+"\n" +
                    "În ultima vreme aţi avut: - o pierdere în greutate neasteptata, "+selectat2.getText()+"\n" +
                    "                          - febra neexplicabila "+selectat3.getText()+"\n" +
                    "                          - tratament stomatologic, vaccinari "+selectat4.getText()+"\n" +
                    "2. Urmati vreun tratament medicamentos? "+ selectat5.getText()+"\n" +
                    "3. În ultimele 12 luni ati avut contact sexual cu:\n" +
                    "       - un partener cu hepatita sau HIV pozitiv "+selectat6.getText()+"\n" +
                    "       - un partener ce se drogheaza prin injectii "+selectat7.getText()+"\n" +
                    "       - un partener care este platit pentru sex "+selectat8.getText()+"\n" +
                    "       - un partener cu hemofilie "+selectat9.getText()+"\n" +
                    "       - parteneri multipli "+selectat10.getText()+"\n" +
                    "*V-ati injectat vreodata droguri? "+selectat11.getText()+"\n" +
                    "*Ati acceptat bani sau droguri pentru a intretine relatii sexuale? "+selectat12.getText()+"\n" +
                    "*pentru barbati: ati avut relatii sexuale cu un alt barbat? "+selectat13.getText()+"\n" +
                    "*pentru femei: a avut partenerul dumneavoastra relatii sexuale\n" +
                    "cu un alt barbat? " + selectat14.getText()+"\n" +
                    "*ati schimbat partenerul (partenera) în ultimele 6 luni? "+selectat15.getText()+"\n" +
                    "4. De la ultima donare, sau în ultimele 12 luni ati suferit:\n" +
                    "- o interventie chirurgicala sau investigatii medicale? "+ selectat16.getText()+"\n" +
                    "- tatuaje, acupunctura, gauri pentru cercei? "+selectat17.getText()+"\n" +
                    "- ati fost transfuzat (a)? "+selectat18.getText()+"\n" +
                    "- ati fost însarcinata? "+selectat19.getText()+"\n" +
                    "pentru femei:\n" +
                    "- data nasterii ultimului copil: "+editText2.getText()+"\n" +
                    "- data ultimei menstruaţii: "+editText3.getText()+"\n" +
                    "5.V-ati nascut, ati trait sau calatorit in strainatate? "+selectat20.getText()+"\n" +
                    "Unde? "+editText4.getText()+"\n" +
                    "6.Ati fost în detenţie (INCHISOARE) în ultimul an? "+selectat21.getText()+"\n" +
                    "7.Ati fost expusi la hepatita(bolnavi în familie sau risc profesional)? "+selectat22.getText()+"\n" +
                    "8.Ati suferit vreodată de:\n" +
                    "- icter, tuberculoza, febra reumatică, malarie? "+selectat23.getText()+"\n" +
                    "- boli de inimă, tensiune arteriala mare sau mica? "+selectat24.getText()+"\n" +
                    "- boli transmise sexual (hiv, sifilis etc) "+selectat25.getText()+"\n" +
                    "- accidente vasculare cardiace sau cerebrale? "+selectat26.getText()+"\n" +
                    "- convulsii, boli nervoase? "+selectat27.getText()+"\n" +
                    "- boli cronice (diabet, ulcer, cancer, astm) ? "+selectat28.getText()+"\n" +
                    "*Sunteti fumator? "+selectat29.getText()+"\n" +
                    "*Ati consumat alcool recent? "+selectat30.getText()+"\n" +
                    "*Ce bauturi ati consumat? "+editText5.getText()+"\n" +
                    "*În ce cantitate? "+editText6.getText()+"\n" +
                    "9.Aţi fost refuzat sau amanat la o donare anterioara? "+selectat31.getText()+"\n" +
                    "10. Aveti pasiuni sau ocupatii ce necesita atentie speciala 24 ore\n" +
                    "postdonare (de ex: sofer, alpinist, scafandru, etc)? "+selectat32.getText()+"\n" +
                    "Declar pe propria raspundere/Semnatura\n" +
                    "Semnatura persoanei care supravegheaza completarea chestionarului\n" +
                    "Semnatura medicului din cadrul cabinetului de triaj\n" +
                    "CE TREBUIE SA STIE DONATORII DE SANGE\n" +
                    "a). Importanta sangelui, procesul de donare de sange si principalele beneficii ale pacientului\n" +
                    "transfuzat;\n" +
                    "b). Protectia si confidentialitatea datelor personale ale donatorului, respectiv identitatea si starea\n" +
                    "de sanatate a acestuia , rezultatele testelor efectuate, care nu vor fi furnizate fara autorizare;\n" +
                    "c). Natura procedurilor implicate in donarea de sange sau de componentele sanguine destinate a\n" +
                    "fi transfuzate unor alte persoane si riscurile asociate fiecareia in parte;\n" +
                    "d). Optiunea donatorilor de a se razgandi in ceea ce priveste actul donarii, inainte de a merge\n" +
                    "mai departe sau despre eventualitatea de a se autoexclude in orice moment in cursul procesului de\n" +
                    "donare, fara nici un fel de jena sau discomfort;\n" +
                    "e). Donatorii sa informeze centrul de transfuzie sanguina despre orice eventual eveniment ulterior\n" +
                    "transfuziei ce poate face donarea anterioara improprie pentru terapia transfuzionala;\n" +
                    "f). Responsabilitatea centrului de transfuzie sanguina de a informa donatorul printr-un mecanism\n" +
                    "adecvat care sa asigure pastrarea confidentialitatii , daca rezultatele testelor arata vreo anomalitate\n" +
                    "cu semnificatie asupra starii de sanatate a donatorului; crearea de mecanisme de informare a\n" +
                    "donatorului, ulterior donarii, motivelor pentru care unitatile de sange si componente sanguine\n" +
                    "nefolosite provenite din donarea autologa, vor fi rebutate si nu transfuzate altor pacienti;\n" +
                    "h). Rezultatele testelor ce detecteaza markeri pentru virusuri sau agenti microbieni cu transmitere\n" +
                    "transfuzionala vor duce la excluderea donatorilor si distrugerea unitatilor de sange colectate;\n" +
                    "i). Donatorii sa puna intrebari in orice moment;\n" +
                    "j). La selectia potentialilor donatori, donarea se realizeaza exclusiv la sediul unde se efectueaza\n" +
                    "recoltarea numai de catre personalul instruit al centrelor de transfuzie sanguina teritoriale (CTS),\n" +
                    "precum si la sediul celui mai apropiat centru de transfuzie sanguina;\n" +
                    "k). Donarea de sange, in Romania este voluntara si neremunerata; persoanele care au donat sau\n" +
                    "doneaza sange nu pot primi recompense de natura materiala, cum ar fi premii in bani, gratificatii\n" +
                    "salariale, pensie de stat sau ajutor social, pentru faptul ca au donat sange sau componente sanguine;\n" +
                    "l). Beneficiile si riscurile pentru sanatate ale donarii de sange;\n" +
                    "m). Explicarea criteriilor de excludere pentru donatorii de sange;\n" +
                    "n). Existenta si semnificatia “consimtamantului informat”;\n" +
                    "o). Importanta efectuarii examinarii medicale si a testelor obligatorii, solicitatea antecedentelor\n" +
                    "fiziologice si patologice si motivarea acestora;\n" +
                    "p). Nedeclararea intentionata de catre donator a bolilor transmisibile sau a factorilor de risc\n" +
                    "cunoscuti, constituie infractiune ce se pedepseste conform art.39 si 40 din Legea nr.282/2005;\n" +
                    "q). Pot dona sange la centrul de transfuzie sanguina dintr-un anumit teritoriu cetatenii altor tari,\n" +
                    "care locuiesc in Romania de cel putin un an si care pot prezenta acte doveditoare in acest sens,\n" +
                    "originale sau copii legalizate la notariat;\n" +
                    "r). Cetatenii romani care au domiciliul stabil, serviciul sau studiaza in teritoriul arondat centrului\n" +
                    "de transfuzie sanguina, respectiv cetatenii romani care au donat sange sau componente sanguine\n" +
                    "intr-un alt centru de transfuzie sanguina, dar si-au schimbat domiciliul de cel putin 6 luni in teritoriul\n" +
                    "respectiv si care pot prezenta acte doveditoare, sunt eligibili pentru donarea de sange;\n" +
                    "s). Militarii din unitatile situate in raza teritoriala a centrului de transfuzie sanguina teritorial, pot\n" +
                    "dona sange la acest centru;\n" +
                    "t). Conditiile pe care trebuie sa le indeplineasca potentialul donator in vederea donarii de sange,\n" +
                    "respectiv sa aiba o stare buna de sanatate fizica si mentala, o stare de igiena personala\n" +
                    "corespunzatoare si sa prezinte documentele medicale doveditoare ca a efectuat examenele medicale\n" +
                    "recomandate de medicul responsabil cu selectia donatorilor;\n" +
                    "u). Potentialul donator de sange este eligibil numai in conditiile in care nu apartine grupului care\n" +
                    "prin comportamentul sexual sau/si habitual il plaseaza in zona cu risc de a contacta sau/si de a\n" +
                    "raspandi afectiuni severe ce se pot transmite prin sange.\n\n\n" +
                    "      Data "+dateFormat.format(date)+"\n\n" +
                    "      AM LUAT LA CUNOSTINTA\n\n\n" +
                    "      SEMNATURA\n\n" +
                    "      ____________";
            document.add(new Paragraph(text));
            document.close();
            Toast.makeText(this,"S-a creat pdf-ul!\n"+filePath,Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case STORAGE_CODE:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    savePdf();
                }
                else{
                    Toast.makeText(this,"Permisiune neacceptata",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}