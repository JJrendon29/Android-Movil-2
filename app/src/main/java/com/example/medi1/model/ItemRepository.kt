// app/src/main/java/com/example/medi1/model/ItemRepository.kt
package com.example.medi1.model

import com.ejemplo.tuapp.model.Item
import com.example.medi1.api.MedicamentosApi
import com.example.medi1.api.RetrofitClient
import retrofit2.Callback
import retrofit2.Response

class ItemRepository {
    // Método para obtener datos de la API
    fun getMedicamentosFromApi(callback: (List<MedicamentoResponse>?) -> Unit) {
        val call = RetrofitClient.retrofit.create(MedicamentosApi::class.java)
        call.getMedicamentos().enqueue(object : Callback<List<MedicamentoResponse>> {
            override fun onResponse(
                call: retrofit2.Call<List<MedicamentoResponse>>,
                response: Response<List<MedicamentoResponse>>
            ) {
                if (response.isSuccessful) {
                    // Mapear los datos de la API a nuestro modelo Item
                    val items = response.body()
                    callback(items)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<MedicamentoResponse>>, t: Throwable) {
                callback(null)
            }
        })
    }

    // Mantener el método original como fallback
    fun getAllItems(): List<Item> {
        // Tus datos originales
        return listOf(


            Item(
                id = 1,
                titulo = "Paracetamol",
                descripcion = "Es un analgésico y antipirético utilizado para aliviar el dolor leve a moderado y reducir la fiebre.",
                descripcionlarge = "El paracetamol es un analgésico y antipirético utilizado para aliviar el dolor leve a moderado y reducir la fiebre. Actúa inhibiendo la síntesis de prostaglandinas en el sistema nervioso central. Es uno de los medicamentos más utilizados a nivel mundial por su perfil de seguridad cuando se usa correctamente. A diferencia de otros analgésicos, tiene poco efecto antiinflamatorio. Es eficaz para dolores de cabeza, dolores musculares, dolores menstruales, dolor dental, dolor posquirúrgico y estados febriles. Disponible en múltiples presentaciones como comprimidos, cápsulas, suspensión oral y supositorios. La dosis habitual para adultos es de 500-1000 mg cada 4-6 horas, sin exceder los 4000 mg diarios."
            ),
            Item(
                id = 2,
                titulo = "Ibuprofeno",
                descripcion = "Es un antiinflamatorio no esteroideo (AINE) que reduce el dolor, la inflamación y la fiebre.",
                descripcionlarge = "El ibuprofeno es un antiinflamatorio no esteroideo (AINE) que reduce el dolor, la inflamación y la fiebre. Actúa inhibiendo la producción de prostaglandinas, sustancias que median en los procesos inflamatorios. Es efectivo en el tratamiento de dolores articulares, dolores musculares, cefaleas, dismenorrea, dolor dental y estados febriles. A diferencia del paracetamol, posee un marcado efecto antiinflamatorio. Se administra por vía oral, habitualmente en dosis de 200-400 mg cada 4-6 horas para adultos, sin exceder los 1200 mg diarios salvo prescripción médica. Puede causar irritación gástrica, por lo que se recomienda su administración junto con alimentos. Está contraindicado en pacientes con úlcera péptica activa, insuficiencia renal grave o antecedentes de hipersensibilidad a otros AINEs."
            ),
            Item(
                id = 3,
                titulo = "Amoxicilina",
                descripcion = "Es un antibiótico betalactámico del grupo de las aminopenicilinas de amplio espectro utilizado en el tratamiento de infecciones bacterianas.",
                descripcionlarge = "La amoxicilina es un antibiótico betalactámico del grupo de las aminopenicilinas de amplio espectro utilizado en el tratamiento de infecciones bacterianas. Actúa inhibiendo la síntesis de la pared celular bacteriana, lo que provoca la muerte de las bacterias sensibles. Es eficaz contra muchas bacterias gram-positivas y algunas gram-negativas. Se utiliza comúnmente para tratar infecciones respiratorias, infecciones de oído, infecciones urinarias, sinusitis, y algunas infecciones de la piel. La dosis habitual para adultos es de 500-875 mg cada 8-12 horas dependiendo de la gravedad de la infección. Es importante completar todo el tratamiento prescrito, incluso si los síntomas mejoran antes de finalizar, para prevenir el desarrollo de resistencias bacterianas. Puede tomarse con o sin alimentos, aunque la absorción es mejor si se toma con el estómago vacío."
            ),
            Item(
                id = 4,
                titulo = "Omeprazol",
                descripcion = "Es un inhibidor de la bomba de protones (IBP) que reduce la producción de ácido en el estómago.",
                descripcionlarge = "El omeprazol es un inhibidor de la bomba de protones (IBP) que reduce la producción de ácido en el estómago al bloquear la enzima H+/K+ ATPasa de las células parietales. Es utilizado para tratar úlceras gástricas y duodenales, reflujo gastroesofágico (ERGE), síndrome de Zollinger-Ellison y como parte del tratamiento para erradicar Helicobacter pylori. También se usa para prevenir úlceras en pacientes que toman medicamentos antiinflamatorios no esteroideos (AINEs) de forma prolongada. La dosis habitual es de 20-40 mg una vez al día, preferiblemente por la mañana en ayunas. El tratamiento suele durar entre 4-8 semanas según la patología a tratar. Se recomienda tragar las cápsulas enteras, sin masticar ni triturar, ya que contienen microgránulos con recubrimiento entérico que protegen el medicamento del ácido gástrico."
            ),
            Item(
                id = 5,
                titulo = "Loratadina",
                descripcion = "Es un antihistamínico de segunda generación que bloquea los receptores H1 de la histamina, aliviando los síntomas alérgicos.",
                descripcionlarge = "La loratadina es un antihistamínico de segunda generación que bloquea los receptores H1 de la histamina, aliviando los síntomas alérgicos como estornudos, picazón, secreción nasal y ojos llorosos. A diferencia de los antihistamínicos de primera generación, causa menos somnolencia ya que no atraviesa fácilmente la barrera hematoencefálica. Se utiliza en el tratamiento de la rinitis alérgica estacional, rinitis alérgica perenne y urticaria idiopática crónica. La dosis habitual para adultos y niños mayores de 12 años es de 10 mg una vez al día. Para niños de 2 a 12 años, la dosis es de 5 mg una vez al día. Se puede tomar con o sin alimentos. El inicio de acción es aproximadamente 1-3 horas tras la administración, con una duración del efecto de hasta 24 horas, lo que permite una dosificación única diaria. Es generalmente bien tolerado, con efectos secundarios leves como dolor de cabeza, fatiga o sequedad de boca."
            ),
            Item(
                id = 6,
                titulo = "Atorvastatina",
                descripcion = "Es un medicamento del grupo de las estatinas que reduce los niveles de colesterol en sangre.",
                descripcionlarge = "La atorvastatina es un medicamento del grupo de las estatinas que reduce los niveles de colesterol en sangre. Actúa inhibiendo la enzima HMG-CoA reductasa, clave en la síntesis de colesterol en el hígado. Está indicada para reducir el colesterol total, el colesterol LDL ('malo'), los triglicéridos y para aumentar el colesterol HDL ('bueno') en pacientes con hipercolesterolemia primaria y dislipidemia mixta. También se utiliza en la prevención primaria y secundaria de enfermedades cardiovasculares. La dosis habitual oscila entre 10 y 80 mg una vez al día, preferiblemente por la noche, ya que la síntesis de colesterol es mayor durante la noche. Los efectos sobre los niveles lipídicos suelen ser evidentes tras 1-2 semanas de tratamiento, con el efecto máximo a las 4 semanas. Es importante realizar análisis periódicos de función hepática durante el tratamiento y evitar el consumo de zumo de pomelo, que puede aumentar la concentración del fármaco en sangre."
            ),
            Item(
                id = 7,
                titulo = "Metformina",
                descripcion = "Es un antidiabético oral del grupo de las biguanidas, utilizado como tratamiento de primera línea para la diabetes mellitus tipo 2.",
                descripcionlarge = "La metformina es un antidiabético oral del grupo de las biguanidas, utilizado como tratamiento de primera línea para la diabetes mellitus tipo 2. Actúa principalmente reduciendo la producción hepática de glucosa, aumentando la sensibilidad a la insulina en tejidos periféricos y disminuyendo la absorción intestinal de glucosa. A diferencia de otros antidiabéticos, no provoca hipoglucemia cuando se usa en monoterapia ni aumento de peso. También puede tener efectos beneficiosos sobre los lípidos plasmáticos. El tratamiento suele iniciarse con dosis bajas (500-850 mg/día) con las comidas, aumentando gradualmente según tolerancia y respuesta hasta 2000-2500 mg/día divididos en 2-3 tomas. Los efectos adversos más comunes son gastrointestinales (náuseas, diarrea, dolor abdominal), que suelen ser transitorios y disminuyen al tomar el medicamento con las comidas. La acidosis láctica es un efecto adverso raro pero potencialmente grave, por lo que está contraindicada en pacientes con insuficiencia renal, hepática o cardíaca grave."
            ),
            Item(
                id = 8,
                titulo = "Alprazolam",
                descripcion = "Es una benzodiacepina de acción corta con propiedades ansiolíticas, sedantes, hipnóticas y anticonvulsivantes.",
                descripcionlarge = "El alprazolam es una benzodiacepina de acción corta con propiedades ansiolíticas, sedantes, hipnóticas y anticonvulsivantes. Actúa potenciando el efecto inhibitorio del neurotransmisor GABA en el sistema nervioso central. Está indicado principalmente para el tratamiento de los trastornos de ansiedad, trastorno de pánico con o sin agorafobia, y como tratamiento coadyuvante de la ansiedad asociada a depresión. La dosis habitual para el tratamiento de la ansiedad en adultos es de 0.25-0.5 mg tres veces al día, pudiendo aumentarse según necesidad y tolerancia. Para el trastorno de pánico, la dosis suele ser mayor, comenzando con 0.5 mg tres veces al día y aumentando hasta una dosis total de 5-6 mg/día en algunos casos. Presenta potencial de dependencia y tolerancia con el uso prolongado, por lo que se recomienda utilizar la dosis más baja eficaz durante el menor tiempo posible. La suspensión del tratamiento debe realizarse gradualmente para evitar el síndrome de abstinencia."
            )
        )
    }

    // Una función para obtener un ítem específico por su ID
    fun getItemById(id: Int): Item? {
        return getAllItems().find { it.id == id }
    }
}