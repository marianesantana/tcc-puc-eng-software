package com.example.examine_ai.ui.components

import android.annotation.SuppressLint
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
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examine_ai.R
import com.example.examine_ai.ui.presentation.users.PDFViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.text.PDFTextStripper
import java.io.InputStream

@SuppressLint("QueryPermissionsNeeded")
@Preview
    @Composable
    fun UploadExame() {
        val viewModel: PDFViewModel = viewModel()

        val isDialogVisible by viewModel.isDialogVisible.observeAsState(false)

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
                        Log.d("BLOCKS: ", blocks.toString())
                    }
                }
            }

        val pickPdfLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri: Uri? = result.data?.data
                try {
                    uri?.let {
                        val inputStream = context.contentResolver.openInputStream(it)
                        if (inputStream != null) {
                            val inputStreamText = extractTextFromPdf(inputStream)

                            viewModel.updateValue(inputStreamText)
                            viewModel.updateExtractedValue(inputStream)
                            viewModel.definirTextoAdicionado(inputStreamText)
                            Log.d("valores setados", "linha 78")
                            Log.d("valores inputStream: ", inputStream.toString())
                            Log.d("valores extractedText: ", inputStreamText)

                            viewModel.showDialog()

                        }
                    }

                } catch (e: Exception){
                    e.printStackTrace()
                }
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
                val photoCamera = R.drawable.baseline_camera_alt_24
                SvgImage(photoCamera)

                    Text("Camera")
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = {
                pickPdfLauncher.launch(pickPdfIntent)

            }) {
                Icon(Icons.Filled.Add, contentDescription = "Upload", tint = Color.White)
                Text("Upload")
            }

            if (isDialogVisible){
                val texto = viewModel.obterExtractedText()
                AlertDialog(onDismissRequest = { viewModel.hideDialog() }, confirmButton = {
                    Button(onClick = {
                        viewModel.hideDialog()
                    }) {
                        Text(text = "Ok")
                    }
                }, text = {
                    Text("Exame lido com sucesso!")
                    if (texto != null) {
                        Text(text = texto)
                    }

                })
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
fun extractTextFromPdf(inputStream: InputStream): String {
    val document = PDDocument.load(inputStream)
    val textStripper = PDFTextStripper()
    val text = textStripper.getText(document)
    document.close()

    return text

}








