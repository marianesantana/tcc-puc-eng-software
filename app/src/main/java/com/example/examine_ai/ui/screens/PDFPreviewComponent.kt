package com.example.examine_ai.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.examine_ai.ui.presentation.users.PDFViewModel
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.rendering.PDFRenderer
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

@Composable
fun PDFPreviewComponent(inputStream: InputStream, extractedText: String){
    val context = LocalContext.current

    Log.d("tela PDF", inputStream.toString())

    val image = inputStream.let { generateImageFromPdf(context, it) }
    val imageBitmap: ImageBitmap = image.asImageBitmap()

    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(375.dp)
            .height(812.dp)
            .background(color = Color(0xB2344054))
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 80.dp)
    ) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp)){
                Column {
                    Text(text = "PDF Preview")
                    Image(
                        bitmap = imageBitmap,
                        contentDescription = null
                    )

                    Text(extractedText, maxLines = 30, overflow = TextOverflow.Ellipsis)


                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Confirmar")
                        
                    }
                }
            }
        }

    }


}

private fun generateImageFromPdf(context: Context, inputStream: InputStream): Bitmap {

//    val inputStreamString = inputStream.toByteArray(Charsets.UTF_8).inputStream()

    val document = PDDocument.load(inputStream)
    val renderer = PDFRenderer(document)
    val image = renderer.renderImage(0)

    val imageFile = File(context.cacheDir, "pdf_preview.png")
    val imageOutputStream = FileOutputStream(imageFile)

    try {
        image.compress(Bitmap.CompressFormat.PNG, 100, imageOutputStream)
        imageOutputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()

    }
    return BitmapFactory.decodeFile(imageFile.path)
}

@Composable
fun PDFPreview(viewModel: PDFViewModel) {

    val pdfDataState by remember { viewModel.uiState }.observeAsState()

    val texto: String? = viewModel.obterExtractedText()

    val inputStream = pdfDataState?.inputStream
    val extractedText = pdfDataState?.extractedText

    if (texto!= null) {
        Text(text = texto)

//        PDFPreviewComponent(inputStream, texto)
    } else {
        Log.d("Textos nulos", "que tristreza")
    }
}
