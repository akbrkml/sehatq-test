package com.badrun.sehatq_test.utils

import java.io.IOException

class NoConnectivityException : IOException() {
 override val message: String
  get() = "No network available, please check your WiFi or Data connection"
}