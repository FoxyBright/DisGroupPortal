package com.study.disgroupportal.tools

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.EXTRA_SUBJECT
import android.content.Intent.EXTRA_TEXT
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.createChooser
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Environment
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.study.disgroupportal.R
import com.study.disgroupportal.view.components.GrayColor
import com.study.disgroupportal.view.components.WhiteColor
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.Instant.ofEpochMilli
import java.time.LocalDateTime
import java.time.ZoneOffset.systemDefault
import java.time.format.DateTimeFormatter.ofPattern
import java.util.Date
import java.util.Locale.US


@Composable
inline fun <reified T : AndroidViewModel> getViewModel() =
    viewModel<T>(LocalContext.current as ComponentActivity)

inline fun <reified T : AndroidViewModel> ViewModelStoreOwner.getViewModel() =
    ViewModelProvider(this)[T::class.java]

val neededStoragePermissions
    get() = ArrayList<String>().apply {
        if (SDK_INT >= TIRAMISU) {
            add(READ_MEDIA_IMAGES)
        } else {
            add(READ_EXTERNAL_STORAGE)
        }
    }.toList()

fun getSettingsUri(packageName: String): Uri =
    Uri.fromParts("package", packageName, null)

@OptIn(ExperimentalPermissionsApi::class)
fun MultiplePermissionsState.openGallery(
    imagePicker: ManagedActivityResultLauncher<String, Uri?>,
    context: Context
) {
    when {
        allPermissionsGranted -> imagePicker.launch("image/*")

        shouldShowRationale -> context.startActivity(
            Intent(
                ACTION_APPLICATION_DETAILS_SETTINGS
            ).apply {
                addFlags(FLAG_ACTIVITY_NEW_TASK)
                data = getSettingsUri(context.packageName)
            }
        )

        else -> launchMultiplePermissionRequest()
    }
}

fun shareWith(
    context: Context,
    link: String,
    sbj: String
) {
    startActivity(
        context,
        createChooser(Intent(ACTION_SEND).apply {
            type = "text/plain"
            putExtra(EXTRA_SUBJECT, sbj)
            putExtra(EXTRA_TEXT, link)
        }, "ShareWith"),
        null
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun imageTaker(
    resultUri: Uri?,
    onSuccess: Uri.() -> Unit,
): ManagedActivityResultLauncher<Uri, Boolean> {
    val showError = remember {
        mutableStateOf(false)
    }

    if (showError.value) {
        BasicAlertDialog(
            onDismissRequest = {
                showError.value = false
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(16.dp),
                        color = WhiteColor
                    )
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.error_title),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = GrayColor,
                    fontSize = 24.sp
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.error_camera),
                    fontWeight = FontWeight.Normal,
                    color = GrayColor,
                    fontSize = 16.sp
                )

                Spacer(Modifier.height(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = { showError.value = false },
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            fontWeight = FontWeight.SemiBold,
                            text = stringResource(R.string.ok),
                            color = GrayColor,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }

    return rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { resultUri?.run(onSuccess) ?: run { showError.value = true } }
}

fun ManagedActivityResultLauncher<Uri, Boolean>.openCamera(
    context: Context,
    resultUri: MutableState<Uri?>,
    onFailure: () -> Unit = {},
) {
    context.createFile(
        onFailure = onFailure,
        onSuccess = {
            resultUri.value = it
            launch(it)
        }
    )
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, LENGTH_SHORT).show()
}

fun Long.convertToFormatDate(
    pattern: String = "dd.MM.yyyy",
): String = LocalDateTime.ofInstant(
    ofEpochMilli(this),
    systemDefault()
).format(ofPattern(pattern, US))

@SuppressLint("SimpleDateFormat")
fun Context.createFile(
    onSuccess: (Uri) -> Unit,
    onFailure: () -> Unit = {},
) = try {
    File.createTempFile(
        "JPEG_${SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())}",
        ".jpg", getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    )
} catch (ex: IOException) {
    onFailure()
    null
}?.let {
    onSuccess(
        FileProvider.getUriForFile(
            this,
            "com.study.disgroupportal.fileprovider",
            it
        )
    )
} ?: run(onFailure)