package com.example.proyecto1_hpa;






import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {


    //keys del shared preferences
    private static final String PREFS_NAME = "PrefsUserSesion";
    private static final String KEY_USERNAME = "username";
    SharedPreferences prefs;

    //inicialización de los componentes del layout
    private Toolbar TB;
    private DrawerLayout DL;

    private NavigationView NV;

    private ViewPager2 viewPagerValores;

    FloatingActionButton FAB;

    String nombreUsuario, nombre_valor;

    Usuarios usuario;

    ProgressBar progressBar;

    // Sensor y Temas
    SensorManager sensorManager;
    Sensor sensor;
    int THEME = R.style.Base_Theme_Proyecto1_HPA;
    int CurrentSlide = 0;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Recargar();

    }

    //Regenera todo el layout aplicando todo el tema cambiado
    public void Recargar() {

        sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        //extrae el tema para luego aplicarlo en la vista
        THEME = sharedPref.getInt("theme", R.style.Base_Theme_Proyecto1_HPA);
        setTheme(THEME);

        // SENSOR, TEMAS, FIN

        setContentView(R.layout.activity_main);

        // SENSOR, TEMAS

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        sensorManager.registerListener( (SensorEventListener) this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        // SENSOR, TEMAS, FIN

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        nombreUsuario = prefs.getString(KEY_USERNAME, null);

        //Intent intent = getIntent();
        Intent intentComentarios=new Intent(MainActivity.this, PantallaComentarios.class);

        //asignación de los elementos a las variables
        TB=findViewById(R.id.TB);
        setSupportActionBar(TB);
        DL=(DrawerLayout) findViewById(R.id.DL);
        NV=(NavigationView)findViewById(R.id.NV);

        FAB=(FloatingActionButton) findViewById(R.id.FAB);

        progressBar = findViewById(R.id.progressBar);

        //código para llenar la info del viewpager
        viewPagerValores = findViewById(R.id.viewPager2);



        //inicialización de la lista de los slides
        List <Valores> lista;
        lista = new ArrayList<Valores>();


        //asignación de los valores de todas las slides del ViewPager
        lista.add(new Valores("Respeto", "Respeto: \"La verdadera esencia del respeto radica en reconocer la valía inherente de cada ser humano, construyendo puentes de comprensión y aceptación mutua.\"\n" +
                "\n" +
                "El respeto se distingue por la consideración y aprecio hacia los demás, fomentando así un ambiente de comprensión y aceptación en las interacciones humanas.\n" +
                "\n" +
                "Importancia del Respeto:\n" +
                "Este valor juega un papel esencial en varios aspectos de la vida, destacando su relevancia en:\n" +
                "1. Fomento de Relaciones Saludables: El respeto establece la base para relaciones saludables al reconocer y valorar la diversidad de perspectivas y experiencias.\n" +
                "  \n" +
                "2. Cultivo de la Empatía: Contribuye al desarrollo de la empatía al ponerse en el lugar del otro, fortaleciendo los lazos sociales y emocionales.\n" +
                "\n" +
                "Cómo Practicar el Respeto: \n" +
                "Para incorporar de manera efectiva el respeto en la vida diaria, se pueden seguir los siguientes principios:\n" +
                "\n" +
                "1. Escucha Activa: Practicar una escucha activa y genuina, mostrando interés y respeto por las opiniones de los demás.\n" +
                "  \n" +
                "2. Tolerancia y Aceptación: Fomentar la tolerancia y la aceptación de las diferencias, reconociendo la riqueza que aporta la diversidad.\n" +
                "\n" +
                "Comparación Breve: Vida con y sin Respeto:\n" +
                "En una vida sin respeto, se observa la posibilidad de relaciones tensas y falta de comprensión. La ausencia de este valor puede conducir a malentendidos y conflictos. En contraste, una vida con respeto se caracteriza por relaciones enriquecedoras, donde la diversidad es celebrada, generando un ambiente de armonía y crecimiento personal.\n" +
                "\n" +
                "El respeto, como virtud fundamental, actúa como un cimiento para relaciones saludables y una convivencia armoniosa. Integrar este valor en la vida cotidiana no solo mejora las interacciones personales, sino que también contribuye al bienestar colectivo y al desarrollo de sociedades más equitativas y comprensivas.\n", R.drawable.valor_respeto));
        lista.add(new Valores("Responsabilidad", "Responsabilidad: \"En la responsabilidad yace el poder transformador de nuestras acciones, reconociendo que nuestras decisiones no solo afectan nuestro propio destino, sino también el bienestar de quienes nos rodean y el mundo que compartimos.\"\n" +
                "\n" +
                "La responsabilidad se distingue por asumir conscientemente las consecuencias de nuestras acciones, reconociendo la influencia directa que tenemos en nuestro entorno.\n" +
                "\n" +
                "Importancia de la Responsabilidad:\n" +
                "Este valor juega un papel esencial en varios aspectos de la vida, destacando su relevancia en:\n" +
                "\n" +
                "1. Crecimiento Personal: La responsabilidad propicia un constante crecimiento personal al ser conscientes de nuestras elecciones y aprender de las experiencias.\n" +
                "  \n" +
                "2. Contribución al Bien Común: Fomenta la construcción de comunidades saludables al reconocer la importancia de nuestras acciones en el bienestar colectivo.\n" +
                "\n" +
                "Cómo Practicar la Responsabilidad: \n" +
                "Para incorporar de manera efectiva la responsabilidad en la vida diaria, se pueden seguir los siguientes principios:\n" +
                "\n" +
                "1. Autoevaluación Constante: Reflexionar regularmente sobre nuestras decisiones y asumir la responsabilidad de las consecuencias, tanto positivas como negativas.\n" +
                "  \n" +
                "2. Compromiso con el Cambio Positivo: Actuar de manera proactiva para contribuir al cambio positivo, reconociendo nuestro papel en la creación de un entorno más justo y sostenible.\n" +
                "\n" +
                "Comparación Breve: Vida con y sin Responsabilidad:\n" +
                "En una vida sin responsabilidad, se observa una falta de dirección y un impacto negativo en las relaciones personales y en la sociedad en general. La ausencia de este valor puede conducir a la irresponsabilidad y la evasión de consecuencias. En contraste, una vida con responsabilidad se caracteriza por la autorreflexión, la toma de decisiones informada y la contribución positiva al bien común.\n" +
                "\n" +
                "La responsabilidad, como virtud fundamental, actúa como un faro que guía nuestras acciones hacia el crecimiento personal y la construcción de comunidades más fuertes y equitativas. Integrar este valor en la vida cotidiana no solo fortalece el carácter individual, sino que también contribuye al florecimiento colectivo y al desarrollo sostenible de nuestras comunidades.\n", R.drawable.valor_responsabilidad));
        lista.add(new Valores("Honestidad", "Honestidad: \"En la honestidad, se forja el puente de confianza que conecta las relaciones humanas. Es el compromiso inquebrantable con la verdad, un faro que ilumina el camino hacia la integridad personal y la fortaleza de las conexiones humanas.\"\n" +
                "\n" +
                "La honestidad se distingue por la sinceridad y la transparencia en las palabras y acciones, construyendo así la base fundamental de la confianza mutua.\n" +
                "\n" +
                "Importancia de la Honestidad:\n" +
                "Este valor juega un papel esencial en varios aspectos de la vida, destacando su relevancia en:\n" +
                "\n" +
                "1. Construcción de Confianza: La honestidad es la piedra angular para construir y mantener relaciones sólidas y duraderas.\n" +
                "  \n" +
                "2. Desarrollo de la Integridad Personal: Fomenta la coherencia entre los valores internos y las acciones externas, promoviendo así la integridad personal.\n" +
                "\n" +
                "Cómo Practicar la Honestidad: \n" +
                "Para incorporar de manera efectiva la honestidad en la vida diaria, se pueden seguir los siguientes principios:\n" +
                "\n" +
                "1. Decir la Verdad Siempre: Priorizar la verdad en todas las interacciones, incluso cuando pueda ser incómoda.\n" +
                "  \n" +
                "2. Aceptar la Responsabilidad: Reconocer errores y asumir la responsabilidad de las consecuencias, promoviendo así una cultura de honestidad y crecimiento personal.\n" +
                "\n" +
                "Comparación Breve: Vida con y sin Honestidad:\n" +
                "En una vida sin honestidad, se observa la fragilidad de las relaciones y la falta de integridad personal. La ausencia de este valor puede conducir a la desconfianza y la disolución de conexiones significativas. En contraste, una vida con honestidad se caracteriza por relaciones auténticas, basadas en la confianza mutua y el respeto genuino.\n" +
                "\n" +
                "La honestidad, como virtud fundamental, actúa como el cimiento sobre el cual se construyen relaciones sólidas y se forja el carácter personal. Integrar este valor en la vida cotidiana no solo fortalece la calidad de las interacciones humanas, sino que también contribuye a la formación de comunidades basadas en la confianza y la autenticidad.\n", R.drawable.valor_honestidad));
        lista.add(new Valores("Tolerancia", "Tolerancia: \"En la tolerancia, encontramos la fuerza para abrazar la diversidad y la capacidad de construir puentes de entendimiento entre visiones del mundo divergentes. Es la piedra angular de sociedades inclusivas y armoniosas, donde la aceptación reemplaza el temor y la comprensión supera la ignorancia.\"\n" +
                "\n" +
                "Definición de la Tolerancia:\n" +
                "La tolerancia se distingue por la aceptación respetuosa de las diferencias individuales y la disposición a comprender y convivir con diversas perspectivas y formas de vida.\n" +
                "\n" +
                "Importancia de la Tolerancia:\n" +
                "Este valor juega un papel esencial en varios aspectos de la vida, destacando su relevancia en:\n" +
                "\n" +
                "1. Fomento de la Diversidad: La tolerancia crea un espacio donde la diversidad es no solo aceptada, sino valorada como una fortaleza.\n" +
                "  \n" +
                "2. Prevención de Conflictos: Contribuye a la prevención de conflictos al promover el diálogo y la colaboración en lugar de la confrontación.\n" +
                "\n" +
                "Cómo Practicar la Tolerancia: \n" +
                "Para incorporar de manera efectiva la tolerancia en la vida diaria, se pueden seguir los siguientes principios:\n" +
                "\n" +
                "1. Escucha Activa y Empatía: Practicar la escucha activa y esforzarse por comprender las experiencias y perspectivas de los demás.\n" +
                "\n" +
                "2. Respeto a la Autenticidad: Reconocer y respetar la autenticidad de cada individuo, independientemente de sus diferencias.\n" +
                "\n" +
                "Comparación Breve: Vida con y sin Tolerancia:\n" +
                "En una vida sin tolerancia, se observa la presencia de prejuicios, discriminación y conflictos innecesarios. La ausencia de este valor puede conducir a la segregación y la alienación. En contraste, una vida con tolerancia se caracteriza por la armonía social, el respeto por la diversidad y la construcción de comunidades inclusivas.\n" +
                "\n" +
                "La tolerancia, como virtud fundamental, actúa como el puente que conecta a personas de diferentes trasfondos y experiencias. Integrar este valor en la vida cotidiana no solo enriquece las interacciones humanas, sino que también contribuye a la construcción de sociedades más justas, comprensivas e inclusivas.\n", R.drawable.valor_tolerancia));
        lista.add(new Valores("Humildad", "Humildad: \"En la humildad, descubrimos la verdadera grandeza que surge de reconocer nuestras limitaciones y aprender de cada experiencia. Es la llave que abre puertas a la sabiduría, promoviendo la conexión genuina con los demás y cultivando un crecimiento constante.\"\n" +
                "\n" +
                "La humildad se distingue por la conciencia realista de nuestras habilidades y logros, sin exageraciones ni presunciones, promoviendo así una actitud de respeto hacia uno mismo y hacia los demás.\n" +
                "\n" +
                "Importancia de la Humildad:\n" +
                "Este valor juega un papel esencial en varios aspectos de la vida, destacando su relevancia en:\n" +
                "\n" +
                "1. Fomento de Relaciones Auténticas: La humildad establece las bases para relaciones auténticas al eliminar barreras egocéntricas y fomentar la conexión genuina.\n" +
                "  \n" +
                "2. Crecimiento Personal: Propicia un ambiente propicio para el crecimiento personal al estar abiertos a la retroalimentación y aprender de cada experiencia.\n" +
                "\n" +
                "Cómo Practicar la Humildad: \n" +
                "Para incorporar de manera efectiva la humildad en la vida diaria, se pueden seguir los siguientes principios:\n" +
                "\n" +
                "1. Evitar la Presunción: Abstenerse de presumir o exagerar logros personales, permitiendo que los méritos hablen por sí mismos.\n" +
                "  \n" +
                "2. Aprender de los Errores: La humildad se manifiesta al admitir errores y estar dispuesto a aprender de ellos, en lugar de buscar justificaciones.\n" +
                "\n" +
                "Comparación Breve: Vida con y sin Humildad:\n" +
                "En una vida sin humildad, se observa el riesgo de relaciones tensas y la falta de crecimiento personal. La ausencia de este valor puede conducir a la presunción, obstaculizando la colaboración y generando un ambiente propenso a malentendidos. En contraste, una vida con humildad se caracteriza por la apertura, el reconocimiento mutuo y el fomento de un crecimiento continuo tanto a nivel personal como colectivo.\n" +
                "\n" +
                "La humildad, como virtud fundamental, actúa como un faro que guía hacia la autenticidad y el crecimiento personal. Integrar este valor en la vida cotidiana no solo fortalece las relaciones interpersonales, sino que también contribuye a un desarrollo sostenible y significativo, tanto a nivel individual como comunitario.\n", R.drawable.valor_humildad));

        lista.add(new Valores("Modestia", "La modestia, como valor humano, se caracteriza por una actitud de humildad y respeto hacia uno mismo y hacia los demás. Este atributo implica una apreciación realista de las propias habilidades y logros, sin exageraciones ni presunciones.\n" +
                "\n" +
                "Importancia de la Modestia\n" +
                "\n" +
                "La modestia desempeña un papel crucial en diversos aspectos de la vida, destacando su relevancia en:\n" +
                "\n" +
                "Fomento de Relaciones Saludables:\n" +
                "\n" +
                "Actúa como un facilitador de relaciones saludables al cultivar la empatía y el reconocimiento de los méritos de los demás.\n" +
                "Crecimiento Continuo:\n" +
                "\n" +
                "La modestia propicia un ambiente propicio para el crecimiento personal al estar abiertos a la retroalimentación y la mejora constante.\n" +
                "Promoción de la Colaboración:\n" +
                "\n" +
                "Facilita la colaboración efectiva al priorizar el trabajo en equipo sobre la búsqueda individual de reconocimiento.\n" +
                "Cómo Practicar la Modestia: Guía Práctica\n" +
                "\n" +
                "Evitar la Presunción:\n" +
                "\n" +
                "Abstenerse de presumir o exagerar logros personales, permitiendo que los méritos hablen por sí mismos.\n" +
                "Reconocer Contribuciones Ajenas:\n" +
                "\n" +
                "Valorar y reconocer las contribuciones de los demás, cultivando un ambiente de respeto mutuo.\n" +
                "Aprender de los Errores:\n" +
                "\n" +
                "La modestia se manifiesta al admitir errores y estar dispuesto a aprender de ellos, en lugar de buscar justificaciones.\n" +
                "Promover la Igualdad:\n" +
                "\n" +
                "Buscar la igualdad en interacciones y relaciones, reconociendo la valía de cada individuo sin jactancias ni actitudes condescendientes.\n" +
                "Comparación Breve: Vida con y sin Modestia\n" +
                "\n" +
                "En una vida sin modestia, el riesgo de relaciones tensas y la falta de crecimiento personal son evidentes. La ausencia de modestia puede conducir a la presunción, obstaculizando la colaboración y generando un ambiente propenso a malentendidos. En contraste, una vida con modestia se caracteriza por la apertura, el reconocimiento mutuo y el fomento de un crecimiento continuo tanto a nivel personal como colectivo. La modestia, como virtud, actúa como un catalizador para relaciones saludables y un desarrollo sostenible.", R.drawable.valor_modestia));
        lista.add(new Valores("Ambición", "La ambición, como valor humano, se define como la búsqueda constante de metas y logros, impulsada por un deseo saludable de crecimiento personal y contribución al entorno. Este atributo positivo implica una determinación firme para alcanzar objetivos significativos.\n" +
                "\n" +
                "Importancia de la Ambición\n" +
                "\n" +
                "La ambición desempeña un papel crucial en diversos aspectos de la vida, destacando su relevancia en:\n" +
                "\n" +
                "Motivación y Dirección:\n" +
                "\n" +
                "Actúa como un catalizador motivacional, proporcionando dirección y propósito a las acciones individuales.\n" +
                "Crecimiento Personal:\n" +
                "\n" +
                "La ambición impulsa el desarrollo personal al fomentar la búsqueda constante de nuevas habilidades, conocimientos y experiencias.\n" +
                "Contribución a la Comunidad:\n" +
                "\n" +
                "Facilita la generación de impacto positivo al buscar constantemente maneras de contribuir al bienestar de la comunidad.\n" +
                "Cómo Practicar la Ambición: Guía Práctica\n" +
                "\n" +
                "Establecer Metas Claras:\n" +
                "\n" +
                "Definir metas concretas y alcanzables, proporcionando un marco claro para la ambición positiva.\n" +
                "Persistencia y Resiliencia:\n" +
                "\n" +
                "Desarrollar la habilidad de persistir ante desafíos, aprendiendo de las adversidades y ajustando enfoques cuando sea necesario.\n" +
                "Colaboración y Empatía:\n" +
                "\n" +
                "Canalizar la ambición hacia esfuerzos colaborativos, reconociendo la importancia de la empatía y la comprensión en el camino hacia el éxito.\n" +
                "Aprender de los Fracasos:\n" +
                "\n" +
                "Ver los fracasos como oportunidades de aprendizaje, aprovechando la ambición para superar obstáculos y mejorar continuamente.\n" +
                "Recomendación Personal: El Arte de la Ambición Sostenible\n" +
                "\n" +
                "Considerar la ambición como un arte que se nutre de la pasión y la disciplina. Al equilibrar la determinación con la sensibilidad hacia el bienestar propio y de los demás, se puede cultivar una ambición sostenible y significativa. La verdadera magia de la ambición radica en su capacidad para inspirar el crecimiento personal y contribuir positivamente al mundo que nos rodea.", R.drawable.valor_ambicion));
        lista.add(new Valores("Valentía", "La valentía, un atributo arraigado en la esfera de la conducta humana, se define como la disposición a afrontar el miedo, la adversidad o el peligro con determinación. Este valor fundamental implica una fuerza interna que impulsa a las personas a actuar incluso en situaciones desafiantes.\n" +
                "\n" +
                "Importancia de la Valentía\n" +
                "\n" +
                "La valentía juega un papel fundamental en diversos aspectos de la vida, destacando su relevancia en:\n" +
                "\n" +
                "Superación de Obstáculos:\n" +
                "\n" +
                "Actúa como un motor para superar barreras y desafíos, permitiendo el crecimiento personal y el avance en situaciones difíciles.\n" +
                "Integridad Personal:\n" +
                "\n" +
                "La valentía está vinculada a la capacidad de actuar de acuerdo con principios éticos, incluso cuando enfrenta oposición.\n" +
                "Innovación y Cambio:\n" +
                "\n" +
                "Fomenta la disposición a asumir riesgos calculados, propiciando la innovación y el progreso en diversas áreas de la vida.\n" +
                "Cómo Practicar la Valentía: Guía Práctica\n" +
                "\n" +
                "Afrontar el Miedo:\n" +
                "\n" +
                "Identificar y confrontar temores personales, tomando medidas para abordarlos gradualmente.\n" +
                "Tomar Decisiones Difíciles:\n" +
                "\n" +
                "La valentía se manifiesta al tomar decisiones difíciles, basadas en la integridad y la convicción personal.\n" +
                "Defender Principios:\n" +
                "\n" +
                "Permanecer firme en la defensa de principios éticos, incluso cuando esto conlleve enfrentar oposición.\n" +
                "Aceptar Desafíos:\n" +
                "\n" +
                "Buscar activamente oportunidades desafiantes y abrazarlas con determinación, promoviendo el crecimiento personal y profesional.\n" +
                "Recomendación Personal: El Poder Transformador de la Valentía\n" +
                "\n" +
                "Reflexionar sobre la valentía como una herramienta transformadora en la vida cotidiana. Al abrazar la valentía, se desbloquea un potencial significativo para enfrentar desafíos, lograr metas ambiciosas y contribuir al cambio positivo. En última instancia, la valentía no solo radica en vencer miedos, sino también en convertirse en la versión más fuerte y auténtica de uno mismo.", R.drawable.valor_valentia));
        lista.add(new Valores("Lealtad", "La lealtad, un principio arraigado en las relaciones humanas, se define como un compromiso firme y constante hacia individuos, grupos o causas específicas. Este valor esencial implica la devoción y la fidelidad, sosteniendo un papel crucial en la construcción y mantenimiento de vínculos duraderos.\n" +
                "\n" +
                "Importancia de la Lealtad\n" +
                "\n" +
                "La lealtad adquiere relevancia en diversos contextos gracias a su influencia en la estabilidad y cohesión social:\n" +
                "\n" +
                "Construcción de Confianza:\n" +
                "   - La lealtad es un componente clave para forjar y mantener la confianza mutua entre individuos y comunidades.\n" +
                "\n" +
                "Consolidación de Relaciones:\n" +
                "   - Actúa como un pegamento emocional, fortaleciendo los lazos afectivos y solidificando las relaciones interpersonales.\n" +
                "\n" +
                "Apoyo Incondicional:\n" +
                "   - La lealtad implica un compromiso sin reservas, proporcionando un respaldo constante incluso en momentos de desafío.\n" +
                "\n" +
                "Cómo Practicar la Lealtad?\n" +
                "\n" +
                "Compromiso Sostenido:\n" +
                "   - Mantener un compromiso constante con personas o causas a lo largo del tiempo, demostrando consistencia en las acciones y decisiones.\n" +
                "\n" +
                "Confidencialidad y Respeto:\n" +
                "   - Resguardar la confidencialidad de información compartida y demostrar respeto hacia la privacidad de aquellos a quienes se es leal.\n" +
                "\n" +
                "Apoyo en Momentos Difíciles:\n" +
                "   - Brindar apoyo incondicional durante períodos de adversidad, mostrando lealtad a través de la presencia y la colaboración.\n" +
                "\n" +
                "Comunicación Abierta:\n" +
                "   - Fomentar una comunicación abierta y honesta, construyendo una base sólida para relaciones leales y duraderas.\n" +
                "\n" +
                "Reflexión Sobre La Lealtad\n" +
                "\n" +
                "Debemos dedicar tiempo a reflexionar sobre el significado personal de la lealtad y cómo se manifiesta en diferentes aspectos de nuestra vida. Evaluar constantemente cómo concuerda lo que hacemos y nuestra relación con la persona a quien se lo hacemos puede fortalecer el compromiso leal, contribuyendo a relaciones más sólidas y satisfactorias. La lealtad, cuando se practica con diligencia y conciencia, se convierte en un faro que guía las interacciones humanas hacia la confianza y la estabilidad.", R.drawable.valor_lealtad));
        lista.add(new Valores("Compasión", "La compasión, una dimensión crucial en la exploración de las relaciones humanas, se define como un estado emocional que va más allá de la simple empatía, manifestándose como la capacidad de sentir y comprender el sufrimiento ajeno, con el deseo intrínseco de aliviar dicho sufrimiento.\n" +
                "\n" +
                "Importancia de la Compasión\n" +
                "\n" +
                "La compasión se presenta como un faro ético en la compleja red de interacciones humanas. Su importancia radica en varios aspectos:\n" +
                "\n" +
                "Conexión Humana:\n" +
                "\n" +
                "La compasión actúa como un puente emocional, fomentando la conexión y comprensión mutua entre individuos y comunidades.\n" +
                "Alivio del Sufrimiento:\n" +
                "\n" +
                "La esencia de la compasión reside en el deseo de mitigar el sufrimiento ajeno, contribuyendo a un entorno más empático y solidario.\n" +
                "Generación de Bienestar:\n" +
                "\n" +
                "La práctica de la compasión promueve un sentido de bienestar colectivo, enriqueciendo la calidad de vida de las personas involucradas.\n" +
                "Cómo Practicar la Compasión: Guía Práctica\n" +
                "\n" +
                "Cultivar la Empatía:\n" +
                "\n" +
                "Desarrollar la capacidad de comprender y compartir las experiencias emocionales de los demás mediante la atención activa.\n" +
                "Actos de Bondad Desinteresada:\n" +
                "\n" +
                "Realizar gestos altruistas que alivien el sufrimiento o mejoren la situación de los demás, sin esperar recompensas.\n" +
                "Abrazar la Vulnerabilidad:\n" +
                "\n" +
                "Reconocer y aceptar tanto la propia vulnerabilidad como la de los demás, facilitando una conexión más auténtica.\n" +
                "Desarrollar la Paciencia:\n" +
                "\n" +
                "Cultivar la paciencia como expresión de compasión hacia aquellos que pueden necesitar más tiempo para comprender o cambiar.\n" +
                "Recomendación Personal: Práctica Continua de la Compasión\n" +
                "\n" +
                "Dedicar un tiempo diario a reflexionar sobre situaciones en las que se puede expresar compasión. Visualizar activamente el sufrimiento ajeno y considerar maneras de contribuir al alivio de dicho sufrimiento puede fortalecer la habilidad de ser compasivo en diversas circunstancias. Esta práctica continua puede integrar la compasión en la vida cotidiana, promoviendo un entorno más colaborativo y humano.", R.drawable.valor_compasion));

        for (Valores valor: lista) {
            valor.save(); //guarda la lista en los valores de acuerdo a su formato
        }

        //inicializa el adaptador del viewpager y lo asigna al ViewPager del layout
        AdaptadorViewPager viewPagerAdaptador = new AdaptadorViewPager( getSupportFragmentManager(), getLifecycle(), lista);
        viewPagerValores.setAdapter(viewPagerAdaptador);

        //inicializa la acción del toggle al Toolbar de lado
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, DL, TB, R.string.open_nav, R.string.close_nav);
        DL.addDrawerListener(toggle);
        toggle.syncState();


        //evento que se ejecuta cada vez que el viewpager cambia de slide
        viewPagerValores.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                progressBar.setProgress(position + 1); //actualiza la posición en el progresbar
                nombre_valor = Valores.findNameById(position+1);

                //mantiene el valor del slide en el viewpager para cuando se cambie el tema
                editor.putInt("slide", viewPagerValores.getCurrentItem());
                editor.apply();
            }
        });

        //evento que se ejecuta cuando se hace click en un item del navigation view
        NV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //cambios en el slide del viewpager de acuerdo al item del navigation view clickeado
                if      (item.getItemId()==R.id.V1){viewPagerValores.setCurrentItem(0);}
                else if (item.getItemId()==R.id.V2){viewPagerValores.setCurrentItem(1);}
                else if (item.getItemId()==R.id.V3){viewPagerValores.setCurrentItem(2);}
                else if (item.getItemId()==R.id.V4){viewPagerValores.setCurrentItem(3);}
                else if (item.getItemId()==R.id.V5){viewPagerValores.setCurrentItem(4);}
                else if (item.getItemId()==R.id.V6){viewPagerValores.setCurrentItem(5);}
                else if (item.getItemId()==R.id.V7){viewPagerValores.setCurrentItem(6);}
                else if (item.getItemId()==R.id.V8){viewPagerValores.setCurrentItem(7);}
                else if (item.getItemId()==R.id.V9){viewPagerValores.setCurrentItem(8);}
                else if (item.getItemId()==R.id.V10){viewPagerValores.setCurrentItem(9);}
                DL.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        //evento de click para el componente FAB (botón de añadir comentarios)
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentComentarios.putExtra("VALOR", nombre_valor);
                startActivity(intentComentarios); //intent hacia el layout the comentarios
            }
        });

        // Recordando antes del cambio de tema
        CurrentSlide = sharedPref.getInt("slide", 0);
        viewPagerValores.setCurrentItem(CurrentSlide, false);


    }
    // SENSOR, TEMAS
    @Override
    public void onSensorChanged(@NonNull SensorEvent sensorEvent) {

        Sensor mySensor = sensorEvent.sensor;
        float LuzActual = sensorEvent.values[0];

        if (mySensor.getType() == Sensor.TYPE_LIGHT) { //ver si el sensor es de tipo de luz

            if (LuzActual < 40 && THEME == R.style.Base_Theme_Proyecto1_HPA) {
                // Cambiar a DarkTheme si la luz es menor a 0.5
                Log.d("Oscuro", String.valueOf(LuzActual));
                THEME = R.style.NightTheme;
                editor.putInt("theme", THEME);//actualizar en el shared preferences
                editor.apply();

                Recargar(); //regeneración del layout

            } else if (LuzActual >= 130 && THEME == R.style.NightTheme) {
                // Cambiar a LightTheme si la luz es mayor a 0.5
                Log.d("Claro", String.valueOf(LuzActual));
                THEME = R.style.Base_Theme_Proyecto1_HPA;
                editor.putInt("theme", THEME); //actualizar en el shared preferences
                editor.apply();

                Recargar(); //regeneración del layout

            }

        }

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No hay que hacer nada aquí todavía...
    }

    // SENSOR, TEMAS, FIN

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //menú de inicialización del menú de opciones
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menutoolbar, menu); //infla el menu toolbar con la información correspondiente
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){ //eventos de cuando se selecciona una opción del menú
        if(item.getItemId()==R.id.close) //ver si se hizo click en el botón de close
        {
            //crea un neuvo AlertDialog
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setPositiveButton("aceptar", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    //toca acpetar, lo manda en un intent a SetName activity
                    Intent intentRegresar=new Intent(MainActivity.this, SetNameActivity.class);
                    startActivity(intentRegresar);
                }
            });

            //si se hace click en cancelar
            builder.setNegativeButton("cancelar", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int id){
                    //Toca cancelar
                    //No ocurre nada, se cierra
                }
            });

            //estilo y texto del AlertDialog
            builder.setTitle("Cerrar sesión");
            builder.setMessage("¿Desea cerrar sesión?");
            AlertDialog dialog=builder.create();
            dialog.show(); //muestra el AlertDialog





        }
        return true;
    }

}

