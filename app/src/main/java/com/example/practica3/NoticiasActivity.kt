package com.example.practica3

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica3.databinding.ActivityNoticiasBinding

class NoticiasActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityNoticiasBinding

    private lateinit var newsAdapter: NoticiasAdapter

    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    private val openURL = Intent(Intent.ACTION_VIEW)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityNoticiasBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE)

        lastTitleNew(preferences)

        newsAdapter = NoticiasAdapter(getNews(), this)

        linearLayoutManager = LinearLayoutManager(this)

        binding.rv.apply {

            layoutManager = linearLayoutManager

            adapter = newsAdapter

        }

    }

    private fun lastTitleNew(preferences: SharedPreferences) {

        val lastTitle = preferences.getString("lastTitle", "")

        if (lastTitle!!.isNotEmpty()) {

            Toast.makeText(this, "Titulo Última Noticias Leída: $lastTitle", Toast.LENGTH_SHORT).show()

        }

    }

    private fun getNews() : MutableList<Noticias> {

        val news = mutableListOf<Noticias>()

        val n1 = Noticias(1,
            "Guerra Ucrania - Rusia, última hora",
            "El líder checheno critica la débil respuesta de Rusia a los ataques de Ucrania en su territorio: Que ni se les ocurra",
            "25/10/2022",
            "https://phantom-elmundo.unidadeditorial.es/fe9459b7b48f460d33f264c02826f9db/crop/0x0/3072x2048/resize/600/f/webp/assets/multimedia/imagenes/2022/10/25/16666751030700.jpg",
            "https://www.elmundo.es/internacional/2022/10/25/63576e0366742a001fac2009-directo.html")

        val n2 = Noticias(2,
            "Huawei Pocket S",
            "Huawei tiene un nuevo plegable tipo concha a punto de salir del horno: el Pocket S ya tiene fecha de presentación",
            "25/10/2022",
            "https://i.blogs.es/0fdd60/1366_2000/1366_2000.jpeg",
            "https://www.xataka.com/moviles/huawei-tiene-nuevo-plegable-tipo-concha-a-punto-salir-horno-pocket-s-tiene-fecha-presentacion")

        val n3 = Noticias(3,
            "Windows Dev Kit",
            "De 'Project Volterra' al Windows Dev Kit: Microsoft lanza un miniPC ARM con 32 GB de RAM que es (casi) un chollo",
            "25/10/2022",
            "https://i.blogs.es/d425e1/captura-de-pantalla-2022-10-25-a-las-9.12.39/500_333.jpeg",
            "https://www.xataka.com/ordenadores/project-volterra-al-windows-dev-kit-microsoft-lanza-minipc-arm-32-gb-ram-que-casi-chollo")

        val n4 = Noticias(4,
            "Redmi Note 12",
            "Los Redmi Note 12 serán bestias en hardware: 200 megapíxeles, potencia de sobra y una edición gaming",
            "25/10/2022",
            "https://i.blogs.es/2da3d5/captura-de-pantalla-2022-10-25-a-las-08.28.50-a.-m./1366_2000.jpeg",
            "https://www.xataka.com/moviles/redmi-note-12-seran-bestias-hardware-200-megapixeles-potencia-sobra-edicion-gaming")

        val n5 = Noticias(5,
            "Gas - Europa",
            "Europa ha pasado de la gran escasez a la gran abundancia de gas. Tanto, que su precio se está yendo a cero",
            "25/10/2022",
            "https://i.blogs.es/4f029e/gas-precio/1366_2000.jpg",
            "https://www.xataka.com/energia/europa-ha-pasado-gran-escasez-a-gran-abundancia-gas-que-su-precio-se-esta-yendo-a-cero")

        val n6 = Noticias(6,
            "Nube Átomica",
            "Un misterio de mil millones de años: hemos descubierto una nube atómica veinte veces más grande que la Vía Láctea",
            "25/10/2022",
            "https://i.blogs.es/549660/stephan-quintet-edit/1366_2000.jpg",
            "https://www.xataka.com/espacio/misterio-mil-millones-anos-hemos-descubierto-nube-atomica-veinte-veces-grande-que-via-lactea")

        val n7 = Noticias(7,
            "Premio Nobel de Física 2022",
            "Premio Nobel de Física 2022: los ganadores son Alain Aspect, John F. Clauser y Anton Zeilinger por su trabajo pionero en la información cuántica",
            "04/10/2022",
            "https://ichef.bbci.co.uk/news/800/cpsprodpb/2B9E/production/_126966111_e2a86c2b-d398-4c03-badc-34cb4c7537fb.jpg.webp",
            "https://www.bbc.com/mundo/noticias-63128140")

        val n8 = Noticias(8,
            "La Tierra en 5000 años",
            "Lisa Kaltenegger: “Hemos descubierto un planeta que nos dice cómo será la Tierra en 5.000 millones de años”",
            "01/10/2022",
            "https://imagenes.elpais.com/resizer/7Rp44Z5YJ5UdIMeFpH2EIubilCs=/1960x0/cloudfront-eu-central-1.images.arcpublishing.com/prisa/B5UFRCRLWZFTHOR66BA4472RMU.JPG",
            "https://elpais.com/ciencia/2022-10-01/lisa-kaltenegger-hemos-descubierto-un-planeta-que-nos-dice-como-sera-la-tierra-en-5000-millones-de-anos.html")

        val n9 = Noticias(9,
            "Imágenes James Webb",
            "Las nuevas imágenes del telescopio ‘James Webb’ muestran planetas gigantes, estrellas agonizantes y galaxias chocando a altísima velocidad",
            "12/07/2022",
            "https://imagenes.elpais.com/resizer/J2Dt1UIXKH9A80IQWw-zoxGRtKk=/828x0/cloudfront-eu-central-1.images.arcpublishing.com/prisa/RTPVEBP5TFGQZC235WLYX5WZHM.jpg",
            "https://elpais.com/ciencia/2022-07-12/las-nuevas-imagenes-del-telescopio-james-webb-muestran-planetas-gigantes-estrellas-agonizantes-y-galaxias-chocando-a-altisima-velocidad.html#?rel=mas")

        val n10 = Noticias(10,
            "Materia Oscura",
            "Desarrollan un sensor para buscar materia oscura en el Universo",
            "13/07/2022",
            "https://imagenes.20minutos.es/files/image_990_v3/uploads/imagenes/2022/07/13/lux-zeplin-lz.jpeg",
            "https://www.20minutos.es/tecnologia/actualidad/desarrollan-un-sensor-para-buscar-materia-oscura-en-el-universo-5029339/")

        news.add(n1)
        news.add(n2)
        news.add(n3)
        news.add(n4)
        news.add(n5)
        news.add(n6)
        news.add(n7)
        news.add(n8)
        news.add(n9)
        news.add(n10)

        return news

    }

    override fun onClick(news: Noticias, url: String, position: Int) {

        val preferences = getPreferences(Context.MODE_PRIVATE)

        preferences.edit().putString("lastTitle", news.titulo).apply()

        openURL.data = Uri.parse(url)

        startActivity(openURL)

    }

}
