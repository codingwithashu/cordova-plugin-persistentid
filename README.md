# Cordova Persistent ID Plugin

## Overview

The Cordova Persistent ID Plugin provides a robust solution for generating and maintaining a persistent unique identifier for your Cordova/Ionic applications on Android devices. This plugin is designed to create an ID that persists across app uninstalls, reinstalls, and version upgrades.

## Features

- Generates a unique identifier that persists across app lifecycles
- Uses multiple fallback methods to ensure consistency
- Handles Android runtime permissions automatically
- Compatible with Cordova and Ionic frameworks

## Installation

To install this plugin, run the following command in your Cordova project:

```bash
cordova plugin add cordova-plugin-persistentid
```

For Ionic projects, use:

```bash
ionic cordova plugin add cordova-plugin-persistentid
```

## Usage

### TypeScript / JavaScript

Here's how to use the plugin in your TypeScript or JavaScript code:

```typescript
declare let persistentId: any;

// ...

persistentId.getUniqueId(
  (id: string) => {
    console.log('Unique ID:', id);
    // Use the ID as needed
  },
  (error: any) => {
    console.error('Error getting unique ID:', error);
  }
);
```

### Ionic Example

For Ionic applications, you might use it in a service like this:

```typescript
import { Injectable } from '@angular/core';
import { Platform } from '@ionic/angular';

@Injectable({
  providedIn: 'root'
})
export class UniqueIdService {
  constructor(private platform: Platform) {}

  async getUniqueId(): Promise<string> {
    return new Promise((resolve, reject) => {
      this.platform.ready().then(() => {
        persistentId.getUniqueId(resolve, reject);
      });
    });
  }
}
```

Then use it in your components:

```typescript
constructor(private uniqueIdService: UniqueIdService) {}

async ngOnInit() {
  try {
    const uniqueId = await this.uniqueIdService.getUniqueId();
    console.log('Unique ID:', uniqueId);
  } catch (error) {
    console.error('Error getting unique ID:', error);
  }
}
```

## How It Works

The plugin uses a multi-step approach to generate and retrieve a persistent ID:

1. First, it checks for an existing ID in the app's SharedPreferences.
2. If not found, it attempts to use the device's serial number (requires permission).
3. If the serial number is unavailable, it falls back to using the Android ID.
4. As a last resort, it generates a new UUID.

The retrieved or generated ID is then stored in SharedPreferences for future use.

## Permissions

This plugin requires the `READ_PHONE_STATE` permission on Android to access the device's serial number. The permission is automatically added to your `AndroidManifest.xml` file, but you need to request it at runtime for Android 6.0 (API level 23) and above.

## Caveats and Considerations

1. **Persistence:** While this plugin aims to provide a persistent ID, it's not 100% guaranteed to survive all scenarios (e.g., factory resets).
2. **Permissions:** Users may deny the `READ_PHONE_STATE` permission, in which case the plugin falls back to other methods.
3. **Android 10+:** Access to the serial number is restricted on Android 10 (API level 29) and above, even with permissions.
4. **Privacy:** Collecting and storing persistent identifiers may have privacy implications. Ensure your app's privacy policy adequately covers this.

## Troubleshooting

If you're experiencing issues:

1. Ensure the plugin is properly installed.
2. Check that you've requested and been granted the necessary permissions.
3. Look for any error messages in the console or plugin callbacks.
4. On Android 10+, be aware that access to the serial number might be restricted.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License.

## Support

If you encounter any issues or have questions, please file an issue on the GitHub repository.

---

Remember to update your app's privacy policy to reflect the use of this plugin and the data it collects.