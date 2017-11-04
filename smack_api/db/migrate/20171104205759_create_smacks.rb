class CreateSmacks < ActiveRecord::Migration[5.1]
  def change
    create_table :smacks do |t|
      t.text :location
      t.integer :slot1

      t.timestamps
    end
  end
end
