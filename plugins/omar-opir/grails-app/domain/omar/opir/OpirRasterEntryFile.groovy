package omar.opir

class OpirRasterEntryFile {

    String name
    String type

    static belongsTo = [opirRasterEntry: OpirRasterEntry]

    static constraints = {
        name()
        type()
    }

    static mapping = {
        name index: 'opir_raster_entry_file_name_idx'
        type index: 'opir_raster_entry_file_type_idx'
        opirRasterEntry index: 'opir_raster_entry_file_raster_entry_idx'
    }
}
