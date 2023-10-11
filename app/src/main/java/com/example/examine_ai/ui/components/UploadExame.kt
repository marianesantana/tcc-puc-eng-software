package com.example.examine_ai.ui.components

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.examine_ai.R
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.text.PDFTextStripper
import java.io.File

@Preview
    @Composable
    fun UploadExame() {
        val context = LocalContext.current
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val pickPdfIntent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "application/pdf"
            addCategory(Intent.CATEGORY_OPENABLE)
        }

        val takePictureLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageBitmap = result.data?.extras?.get("data") as Bitmap
                Log.d("imagem: ", imageBitmap.toString())
                val image = InputImage.fromBitmap(imageBitmap, 0)
                val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
                recognizer.process(image).addOnSuccessListener { text ->
                    val blocks: List<Text.TextBlock> = text.getTextBlocks()
                    Log.d("TEXTO: ", text.text)
                    Log.d("BLOCKS: ", blocks.toString())



                }


//                SuccessComponent()
                // Processar a imagem
            }
        }

        val pickPdfLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri: Uri? = result.data?.data
                Log.d("FILEPATH/URI = ", uri.toString())

//                val inputStream: InputStream? = uri?.let {
//                    context.contentResolver.openInputStream(
//                        it
//                    )
//                }
//                Log.d("INPUT/URI = ", uri.toString())

                extractTextFromPdf(uri.toString())
            }
        }

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Button(onClick = {
                if (takePictureIntent.resolveActivity(context.packageManager) != null) {
                    takePictureLauncher.launch(takePictureIntent)
                }
            }) {
                    SvgImage(resId = R.drawable.camera)
                    Text(" Camera")


            }
            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = {

                pickPdfLauncher.launch(pickPdfIntent)
            }) {
                SvgImage(resId = R.drawable.interface_edit_attachment_1__attachment_link_paperclip_unlink)

                Text("Upload")
            }
        }
    }
@Composable
fun SvgImage(@DrawableRes resId: Int, modifier: Modifier = Modifier) {
    val imageVector = painterResource(id = resId) as? VectorPainter
    if (imageVector != null) {
        Image(
            painter = imageVector,
            contentDescription = null,
            modifier = modifier
        )
    }
}
fun extractTextFromPdf(filePath: String) {
    val docFile = File(filePath)
    Log.d("AAA: ", docFile.toString())
    PDDocument.load(docFile).use { document ->
        Log.d("document: ", document.toString())
         PDFTextStripper().getText(document)
    }
}

